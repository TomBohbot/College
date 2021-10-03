# Data Science Applied Homework 2: HR Analytics: Job Change of Data Scientists:

The purpose of this assignment is to determine the likelyhood that a data scientist will leave their current job. For this assignment we can imagine that we were hired by a company that is actively recruiting for data scientists. This project will give useful information to recruiters so that they can better determine if they should attempt to poach a person from company X to their company. The recruiter will have information that may seem irrelevant about a person and we will make machine learning models to find patterns between that many people they provide and the information provided about them.

# What Metrics Will Be Tracked:
The metrics that will be tracked in this project is the accuracy of our models, the recall, and the precision of each model.

# Deliverables to the Recruiting Team and/or Client:
The recruiters currently have a spreadsheet of thousands of potential data scientists. The deliverable is to indicate which of these data scientists are likely to move to another company. This will save much time for recruiters as they will not have to go through the rejections of people who would not like to leave their job.

# What Is Success Here:
Success here is to build a model that is as accurate as possible while being able to label people who are seeking jobs with high precision. Since there are so many more people that are content with their jobs and do not want to leave their company it is easy to have high accuracy since predicting that everyone will not want to leave their job will give around 75% accuracy as only 25% of data scientist want to leave their job. Therefore maintaining a high precision and recall along with the accuracy is very important as this ensures that the machine learning model is also predicting people who do want to leave their job as well.

# What Are Some Trade-Offs to Discuss:
Initially I wanted to optimize for accuracy as I find this very important in any model. However, since the data is imbalanced as noted above, I believe it is important to optimize for precision and recall even if accuracy dips a bit as it is important to be able to correctly identify the people that want to leave their job as it may is a minority and less common. Another notable trade-off is how much data is in the model. Since the model has many more people that want to stay in their job I decided to make the data set larger to produce a more balanced dataset. Although it increases the size of the data set it can lead to higher precision as it allows the machine learning model to see more people that want to leave their job and in turn can learn more fromt them to be more accurate.
