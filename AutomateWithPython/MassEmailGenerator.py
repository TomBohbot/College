import smtplib

# @utility Send emails with python from Automate the Boring Stuff:
# @author  Tom Bohbot

# This code is derived from the online course, Automate the Boring Stuff
def sendEmails(username, password, recipients, subject, content):
    server = smtplib.SMTP('smtp.gmail.com' , 587) # Connect to smtplib gmail server.
    type(server)
    server
    server.ehlo()
    server.starttls()
    server.login(username, password)
    server.sendmail(username, recipients, subject + content)
    server.quit()


def main():
    username = 'sampleUsername@gmail.com'
    password = 'samplePassword'
    recipients = ['recipientOne@gmail.com', 'recipientTwo@gmail.com', 'recipientThree@gmail.com'] # list of recipients
    subject = 'Subject: Testing sending emails with python\n\n'
    content = 'Content of email.'
    sendEmails(username, password, recipients, subject, content)

main()
