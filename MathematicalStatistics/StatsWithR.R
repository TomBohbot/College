# Setting up dataset:
x = log(c(0.9120,0.9860,1.0600,1.1300,1.1900,1.2600,1.3200,1.3800,1.4100,1.4900))
y = log(c(13.7,15.9,18.5,21.3,23.5,27.2,32.7,36.0,38.6,43.7))
#x = log(c(0.15,0.20,0.25,0.30,0.35,0.40,0.45,0.50,0.55))
#y = log(-log(c(0.95,0.95,0.90,0.85,0.70,0.65,0.60,0.55,0.40)))
  dataset = data.frame(x,y)
  str(dataset)
  n = nrow(dataset)

# Setting up elementary variables:
  meanX = sum(x)/n
  meanY = sum(y)/n
  sxxCentered = sum((x-meanX)*(x-meanX))
  sxyCentered = sum((x-meanX)*(y-meanY))
  syyCentered = sum((y-meanY)*(y-meanY))

# Finding Least Squares Line:
  # Least Squares Line: y = b0 + b1x + error where E(error) = 0
  b1 = sxyCentered/sxxCentered
  b0 = exp(meanY - b1*meanX )
  alpha1 = -1*(sxyCentered/sxxCentered) #ex. when ln(Y) = ln(b0) -b1x. DO THE LINEAR TRANSFORMTIONS HERE AND ALSO WELL AS B1 AND B0. WRITTEN IN ANSWER AS ALPHA ZERO AND ONE.
  alpha0 = exp(meanY - b1*meanX) #if it is lnb0 then do exp(b0)
  print(paste("Least Squares: Y =",b0,"+",b1,"*x"))
  # If the data will change by x amount then the result will change by x*b1. Ex. a one unit change will be a change of 1*b1.

# Plotting the graph with least squares line:
  plot(dataset$x,
       dataset$y,
       main = "Sample Title",
       xlab = "Sample X-Axis",
       ylab = "Sample Y-Axis"
       )
  abline(b0,b1)

# Calculate S^2
  SSE = syyCentered - b1*sxyCentered
  sSquared = SSE/(n-2)  # Should be correct but confirm with professor! Exercise 11.16 is off by one. Answer is 18.04 in book.
  S = sqrt(sSquared)
  
  #Calculate sigma(b0) and sigma(b1) from calculations written in notes above:
  sxx = sum(x*x)
  varB0 = (sSquared*sxx)/(n*sxxCentered)
  varB1 = sSquared/sxxCentered
  sigmaB0 = sqrt(varB0)
  sigmaB1 = sqrt(varB1)
  print("***Results For Part B***")
  print(paste("sigma(b0) =",sigmaB0 ))
  print(paste("sigma(b1) =",sigmaB1 ))

# Find an x% Confidence Interval:
  # 95% confidence interval == estimated interval for p-value.
  perCI = 0.95
  alpha = 1-perCI
  df = n-2
  c00 = sum(x*x)/(n*sxxCentered)
  c11 = 1/sxxCentered
  sqrtc00 = sqrt(abs(c00))
  sqrtc11 = sqrt(abs(c11))
  tValue = -1*qt(alpha/2,df)
  upperBound = b1 + tValue*S*sqrtc11 # How to know when to use b1 and c11 or b0 and c00?
  lowerBound = b1 - tValue*S*sqrtc11
  #upperBound = b0 + tValue*S*sqrtc00 # How to know when to use b1 and c11 or b0 and c00?
  #lowerBound = b0 - tValue*S*sqrtc00
  print(paste(perCI,"Confidence Interval: [",upperBound,",",lowerBound,"]"))

# Null Hypothesis and Alternate Hypothesis:
  # Examples of null and alternate hypothesis! Vary by question!
  # There are 3 options. Find upper, lower, or two tailed and unmute it!
  # Current Case:
    # H0: b1 = 0
    # H1: b1 != 0 
    ExpNullHyp = 0 #Change this per question details
    sigLevel = 0.05#alpha i think.. dont even think its ever used. fill in alpha above!!
  # First find necessary info about t:
    t = (b0-ExpNullHyp)/(S*sqrtc00)   # The zero is really just the value of the null hypothesis.
    #t = (b1-ExpNullHyp)/(S*sqrtc11) # Find out when to use this t formula or the other one!
    newTValue = pt(t,df)
    RR = -1*qt(alpha,df) #CHECK IF *-1
  # Upper-tailed:
    # H0: E(Y) = 0 when x = 0
    # H1: E(Y) > 0 when x = 0
    # print(paste("Should reject if t calculated is greater than t given / reject null hyp. if following statement is true:",t,">",RR )) # ask professor if i need t or newTValue??
  # Lower-tailed:
    # H0: E(Y) = 0 when x = 0
    # H1: E(Y) < 0 when x = 0
    # print(paste("Should reject if t calculated is greater than t given / if following statement is true:",t,"<",-1*RR ))
  # Two-tailed:
    # H0: E(Y)  = 0 when x = 0
    # H1: E(Y) != 0 when x = 0
    print(paste("Should reject if t calculated is greater than t given / if following statement is true:",abs(t),">",tValue ))
  
# Finding the exact p-value:
  # Still need to find out for all the other cases..
  # If the p-value is less then given alpha then reject the null hypothesis.
  # When you must use t-distribution b/c sample size is not large enough to use CLT:
    # Upper-tailed:
    #pValue = 1-pt(t,df)
    # Lower-tailed:
    #pValue = pt(t,df)
    # Two-tailed:
    pValue = 2*(1-pt(t,df) ) # if t is positive
    #pValue = 2*(pt(t,df) )   # if t is negative
  # When you use CLT and obtain a Z score use the normal distribution: just replace pt and qt with pnorm and qnorm.
    # Upper-tailed:
    # Lower-tailed:
    # Two-tailed:
  print(paste("Exact p-Value is:",pValue))
  
  # Finding confidence intervals that use x*:
    newPerCI = 0.90 #Update this info depending on question!
    newAlpha = 1-newPerCI
    xStar = 0.30 # Fill this in with correct number when doing question!
    innerPartOfFormula = sqrt(1/n + ( (xStar-meanX)*(xStar-meanX) )/sxxCentered )
    upperNewCI = b0+b1*xStar+qt(newAlpha/2,df)*S*innerPartOfFormula
    lowerNewCI = b0+b1*xStar-qt(newAlpha/2,df)*S*innerPartOfFormula
    print(paste(newPerCI,"Confidence Interval using x* : [",upperNewCI,",",lowerNewCI,"]"))
  
  # Finding a prediction interval using x*:
    predPer = 0.90 # Alter per question
    predAlpha = 1-predPer
    predXStar = 1.2 #Alter per question
    innerPartOfFormulaPredInterval = sqrt(1 + 1/n + ( (predXStar-meanX)*(predXStar-meanX) )/sxxCentered )
    upperPredInteral  = b0 + b1*predXStar + qt(predAlpha/2 , df)*S*innerPartOfFormulaPredInterval
    lowerPredInteral  = b0 + b1*predXStar - qt(predAlpha/2 , df)*S*innerPartOfFormulaPredInterval
    print(paste(newPerCI,"Prediction Interval using x* : [",upperPredInteral,",",lowerPredInteral,"]"))
    
    
    
