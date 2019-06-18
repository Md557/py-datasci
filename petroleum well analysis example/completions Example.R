setwd("c:/users/thede/Documents/CS-code/Github")
wb<-read.csv("final wb.csv",header= TRUE)
wb_trun<-wb[1:64,]

lin_mod<-lm(EUR.ft~BV+Mobility+xloc+yloc,data=wb_trun)
summary(lin_mod)

lin_mod1b<-lm(EUR.ft~BV+lbs.ft+xloc+yloc,data=wb_trun)
summary(lin_mod1b)
predictions1b<-predict(lin_mod1b,wb_trun)
plot(wb_trun$EUR.ft,predictions1b)
