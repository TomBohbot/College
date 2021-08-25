import redis
import time
from pprint import pprint

# create a redis object:
r = redis.Redis()

# toy values:
QUARTER_MINUTE_IN_SECONDS = 15
VOTE_SCORE = 500
ARTICLES_PER_PAGE = 3

# necessary global variables:
count = 1
last_printed_page = None

# set most recent get function to this var in case printArticles() is called:
most_recent_articles = None

def postAnArticle (user, title, link):
    global count
    current_time = time.time() # set here so that score and now have equal times.
    r.hmset(str(count), {
        "link": link,
        "votes": 1,
        "title": title,
        "user": user,
        "now": current_time,
        "id": count,
        "score": VOTE_SCORE + int(current_time)
    })
    # add to sorted set so i can get by score and time:
    r.zadd("score", {str(count): VOTE_SCORE + current_time})
    r.zadd("time", {str(count): current_time})
    count += 1

def voteForAnArticle (user , articleId):
    # add user to hashset so he cannot vote again:
    first_vote = r.sadd(str(user), str(articleId))
    # ensure too much time has not elapsed and that this person has not already voted:
    if float(r.hget(articleId, "now") ) > time.time() - QUARTER_MINUTE_IN_SECONDS and first_vote == 1:
        r.hincrby(articleId, "score", VOTE_SCORE)
        r.hincrby(articleId, "votes", 1)
        r.zincrby("score", VOTE_SCORE, articleId)
        r.zincrby("votes", 1, articleId)

def getArticlesByScore (page):
    global last_printed_page
    name = "page " + str(page)
    max_desired_article = min(page*ARTICLES_PER_PAGE-1, count) # in the case the last page is desired and it is not full.
    # sort desired articles:
    last_printed_page = r.zrange("score", page*ARTICLES_PER_PAGE-ARTICLES_PER_PAGE, max_desired_article, desc=True) 
    return last_printed_page

def getArticlesByTime(page):
    global last_printed_page
    name_of_list = "page " + str(page)
    # calculate what articles are desired:
    min_desired_article = (page*ARTICLES_PER_PAGE)-ARTICLES_PER_PAGE
    max_desired_article = min(page*ARTICLES_PER_PAGE-1, count) # in the case the last page is desired and it is not full.
    # sort desired articles:
    last_printed_page = r.zrange("time", min_desired_article, max_desired_article, desc=True) 
    return last_printed_page

def printArticles():
    global last_printed_page
    # get the whole article:
    for i in last_printed_page:
        pprint(r.hgetall(i) )

def main():
    print("To see the code snippets after each step please see the source code. It would be redundant to post snippets here as well.")
    print()

    print("1) Reset the Redis server to “initial state” for your database.")
    r.flushall()
    print()

    # Will do both steps within same code chunk: Find out how to do 3
    print("2) Post the following articles in this order:")
    print("3) As you post each of these articles, print the (augmented) “article state” to the terminal:")
    postAnArticle(user="Bob", title="title21", link="http://www.qq.com")
    print("augnmented article state after 1st inserted article")
    pprint(r.hgetall("1") )
    print()
    postAnArticle(user="Bob", title="title180", link="http://www.youtube.com title")
    print("augnmented article state after 2nd inserted article")
    pprint(r.hgetall("2") )
    print()
    postAnArticle(user="Bob", title="title47", link="http://www.vk.com")
    print("augnmented article state after 3rd inserted article")
    pprint(r.hgetall("3") )
    print()
    postAnArticle(user="Jane", title="title94", link="http://www.vk.com")
    print("augnmented article state after 4th inserted article")
    pprint(r.hgetall("4") )
    print()
    postAnArticle(user="Xandra", title="title151", link="http://www.google.co.in")
    print("augnmented article state after 5th inserted article")
    pprint(r.hgetall("5") )
    print()

    print("4) User Joel votes for the first article posted:")
    voteForAnArticle(user="Joel", articleId="1")
    print()

    print("5) Echo the current number of votes associated with that article:")
    print("Votes associated with first inputted article: " +  str(r.hget("1", "votes") ) )
    print()

    print("6) User Joel votes again for the first article posted")
    voteForAnArticle(user="Joel", articleId="1")
    print()

    print("7) Echo the current number of votes associated with that article")
    print("Votes associated with first posted article: " +  str(r.hget("1", "votes") ) )
    print()

    print("8) Invoke ArticlesByScore to retrieve the first page of articles, followed by printArticles for the result-set.")
    first_page_of_articles = getArticlesByScore(1)
    printArticles()
    print()

    print("9) Invoke getArticlesByTime to retrieve the first page of articles, followed by printArticles for the result-set.")
    first_page_of_articles = getArticlesByTime(1)
    printArticles()
    print()

    # # Do 10 and 11 together:
    print("10) Your program should sleep for QUARTER_MINUTE_IN_SECONDS and plus and additional 1000 milliseconds. 11) Echo to the terminal when your program goes to sleep and when it wakes up")
    print("Going to sleep")
    time.sleep(QUARTER_MINUTE_IN_SECONDS + 1)
    print("Just woke up")
    print()

    print("12) User Xandra votes for the second article posted")
    voteForAnArticle(user="Xandra", articleId="2")
    print()

    print("13) Echo the current number of votes associated with that article")
    print("Votes associated with second posted article: " +  str(r.hget("2", "votes") ) )

main()