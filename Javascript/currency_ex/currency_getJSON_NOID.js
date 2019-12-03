var pageSize = 10;
var appID="INSERTAPPIDHERE";
//var code = [[${c.code}]];
var code = window.prompt("Enter a 3 digit currency code to highlight (eg GBP,CND)");
var data_global;
var dataTable_json,dataTable2_json;
var rows_json=[],rows_json2=[];
var curDate;
var options,options2;
(function(){
    google.load("visualization", "1", {packages:["corechart"]});
    google.charts.load('current', {'packages':['bar','table']});
    
    google.setOnLoadCallback(getFX);
	//setOnLoadCallback was only needed in first script, since it has loaded by this second script?
    //getGDP2();
	

})();

//https://www.w3schools.com/howto/howto_google_charts.asp
//https://developers.google.com/chart/interactive/docs/gallery/linechart
//
function getFX(){
    //The getJSON, when used without the $.(,function(){}); returns a more complicated data structure,that would require aditional parsing
    //https://api.jquery.com/jQuery.getJSON/

    //var data_json_test=$.getJSON("http://localhost:8080/worldgdp/api/countries/"+code+"/gdp");
    console.log("data_json w/o function call");
    //console.log(data_json_test['responseJSON']);
    
    $.getJSON("https://openexchangerates.org/api/latest.json?app_id="+appID,function(data_json){
    
        
    //to use the above reference, the web.xml of the Java projectworldgdp\src\main\webapp\static\WEB-INF\ (or Tomcat filters) must be edited
    //to include: Access-Control-Allow-Origin: *  
    //https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Origin
    //http://tomcat.apache.org/tomcat-7.0-doc/config/filter.html#CORS_Filter
    google.load("visualization", "1", {packages:["corechart"]});
    dataTable_json = new google.visualization.DataTable();
    
    
    console.log("data_json=");
    console.log(data_json);
    curDate=new Date(data_json.timestamp*1000);//timestamp in UNIX converted to JavaScript *1000

    dataTable_json.addColumn('string', 'currency');
	dataTable_json.addColumn('number', 'rate/base:'+data_json.base+' on '+curDate.toDateString());  //+data_json.base

    //var rows_json=[];
		//console.log(numeral(646438380568.715).format('($0.00 a)'));
        
		/*data_json.each(data_json, function(item){
			rows_json.push([item.year, item.value])
		});*/
    $.each(data_json.rates, function(item) {
        console.log("each item, value=",item,", ",data_json.rates[item]);
        console.log(data_json.rates[item]);
        //console.log(data_json[item]);
        if(item=='CAD'||item=='GBP'||item=='EUR'||item=='AUD'||item=='CHF')
            rows_json.push([item,data_json.rates[item]]);
        if(item=='GBP'&&data_json.rates[item]>0)
            rows_json.push(['USD/'+item,1/data_json.rates[item]]);
            
    
    });

    for (var obj in data_json){  //https://stackoverflow.com/questions/1078118/how-do-i-iterate-over-a-json-structure

        console.log("for loop obj"+data_json[obj]);
        //console.log("for loop year,value"+data_json[obj].year+", "+data_json[obj].value);
        //rows_json.push([data_json[obj].year,data_json[obj].value]);
    }
    
		dataTable_json.addRows(rows_json);
		
		
		options = {
			hAxis: {
				title: 'Currency',
                //gridlines:{count:10},
                //ticks: [2008,2010,2012,2014,2016,2018],
                //format:'####'

                textStyle:{bold:true,color:"#006644"}
			},
			vAxis: {
				title: 'Rate (ratio)'
                //format:'short'
			},
            /*trendlines: {
                0: {type: 'linear', lineWidth: 3, opacity: .3,showR2:true,visibleInLegend:true},
                1: {type: 'polynomial', degree:2, lineWidth: 3, opacity: .5,showR2:true,visibleInLegend:true}
                
                //1: {type: 'exponential', lineWidth: 10, opacity: .3}
            },*/
            legend:{textStyle:{fontSize:12}},
            title: 'Multiple currency FX Chart w JSON \n'+curDate.toDateString(),
            height:900,
            width:900
	      };
		options2 = {
			hAxis: {
				title: 'Currency',
                //gridlines:{count:10},
                //ticks: [2008,2010,2012,2014,2016,2018],
                //format:'####'

                textStyle:{bold:true,color:"#006644"}
			},
			vAxis: {
				title: 'Rate (ratio)'
                //format:'short'
			},

            legend:{textStyle:{fontSize:12}},
            title: 'Multiple currency ('+code+', '+code2+') FX  Table w/ JSON data',
            width:550,
            height:400
	      };
        

	//Original call for .setOnLoadCallback was not being called properly without the $("#gdp-chart), so added google.setonloadcallback to earlier function. 
    data_global=dataTable_json;
    
    //var chart = new google.visualization.LineChart(document.getElementById('gdp-chart2'));
	//chart.draw(dataTable_json, options);   
        
    getFX2();
        
    /*
    $("#gdp-chart").html('');   //https://www.w3schools.com/jquery/html_html.asp
    //this jquery will set remove the html within this tag.  
    //doesn't seem to be necessary as the "Loading..." dissapears without this statement 
	google.charts.setOnLoadCallback(function(){
			var chart = new google.visualization.LineChart(document.getElementById('gdp-chart'));
			chart.draw(dataTable, options);
		});
	*/
    
    });   //

    
    
	//});   //without using jQUERY getJSON() method https://www.w3schools.com/jquery/ajax_getjson.asp
}
function getFX2(){
    
    /*$.getJSON("https://openexchangerates.org/api/historical/YYYY-MM-DD.json?app_id="+appID,function(data_json){

    });
    */
    getFX2b();
}

function getFX2b(){

    
    var chart = new google.visualization.ColumnChart(document.getElementById('FX-chart'));
	chart.draw(dataTable_json, options);   
    
    dataTable_json.addRows([["https://openexchangerates.org/api/latest.json?app_id="+appID,null]])
    var chart2 = new google.visualization.Table(document.getElementById('FX-table'));
	chart2.draw(dataTable_json, options2);   

    //var chart3 = new google.visualization.AreaChart(document.getElementById('gdp-chart3'));
	//chart3.draw(dataTable_json_joined, options); 

    
}


