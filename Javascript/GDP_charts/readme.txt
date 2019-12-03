The following is an addon to the Spring 5.0 projects Ch 1, Country info & GDP.

The new code runs separately from the project and requires your project 
to be running on a local webserver (ie tomcat) at http://localhost:8080/worldgdp/

The original server had to be adjusted to allow the web-inf to
Access-Control-Allow-Origin
http://tomcat.apache.org/tomcat-7.0-doc/config/filter.html#CORS_Filter

The new code queries in prompt two countries to compare.  Google charts are used to 
show different charting & basic statistics/analytitcs 