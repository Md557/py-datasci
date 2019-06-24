
wb<-read.csv("final wb.csv",header= TRUE)
wb_trun<-wb[1:64,]

lin_mod<-lm(EUR.ft~BVf+Mobility+xloc+yloc,data=wb_trun)
summary(lin_mod)

lin_mod1b<-lm(EUR.ft~BVf+lbs.ft+xloc+yloc,data=wb_trun)
summary(lin_mod1b)
predictions1b<-predict(lin_mod1b,wb_trun)
plot(wb_trun$EUR.ft,predictions1b)
plot(lin_mod1b)

lin_mod2<-lm(EUR.ft~BVf+lbs.ft+yloc,data=wb_trun)
summary(lin_mod2)
predictions2<-predict(lin_mod2,wb_trun)
plot(wb_trun$EUR.ft,predictions2)
plot(lin_mod2)


#Change response to CFF360
lin_mod3<-lm(CFF360~BVf+lbs.ft+xloc+yloc,data=wb_trun)
summary(lin_mod3)
predictions3<-predict(lin_mod3,wb_trun)
plot(wb_trun$EUR.ft,predictions3)
plot(lin_mod3)

#Change response to CFF360, 3 predictors only
lin_mod3<-lm(CFF360~BVf+lbs.ft+xloc+yloc,data=wb_trun)
summary(lin_mod3)
predictions3<-predict(lin_mod3,wb_trun)
plot(wb_trun$EUR.ft,predictions3)
plot(lin_mod3)
