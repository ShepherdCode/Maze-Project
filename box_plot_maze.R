install.packages("readxl")
install.packages("ggplot2")
install.packages("openxlsx")
install.packages("tidyverse")
install.packages(c("Rcpp", "devtools"), dependencies=TRUE)
require(devtools)

library(ggplot2)
library(readxl)
library(openxlsx)
library(tidyverse)

file_name <- "1521653208941"
cellRange <- "A1:D4501"

sheet_name <- paste("output-", file_name, sep="")
file_name <- paste("output-", file_name, ".xls", sep="")


algorithms <- c("dijkstras", "astar-eucl", "bellmanfor", "astar-manh")

for(algorithm in algorithms) {
  sheetName_temp <- paste(sheet_name, "-", algorithm, sep="")
  
  inputData_temp <- read_excel(file_name, sheetName_temp, range=cellRange, col_names = TRUE)
  
  if(algorithm == "dijkstras") {
    inputData <- inputData_temp
  } else {
    inputData <- full_join(inputData, inputData_temp)
  }
  
}

inputData$Size <- as.factor(inputData$Size)
df <- as.data.frame(inputData)

plot <- ggplot(df, aes(x=Size, y=inputData$`Elapsed Time`, fill=Algorithm)) + stat_summary(fun.y=median, geom="point", shape=18,
                                                                                           size=3, aes=aes(group=Algorithm))

# compute lower and upper whiskers
ylim1 = boxplot.stats(df$`Used Memory`)$stats[c(1, 5)]

# scale y limits based on ylim1
plot = plot + coord_cartesian(ylim = ylim1*3)
plot