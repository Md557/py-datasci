var pageSize = 10;
//var code = [[${c.code}]];
var code = window.prompt("Enter a 3 digit country code (eg USA, CAN, MEX)","IND");
var code2 = window.prompt("Enter second country 3 digit code","USA");
var data_global;
var dataTable_json,dataTable2_json;
var rows_json=[],rows_json2=[];
var options,options2;
(function(){
    google.load("visualization", "1", {packages:["corechart"]});
    google.charts.load('current', {'packages':['bar']});
    google.setOnLoadCallback(getGDP2);
	//setOnLoadCallback was only needed in first script, since it has loaded by this second script?
    //getGDP2();
	

})();

//https://www.w3schools.com/howto/howto_google_charts.asp
//https://developers.google.com/chart/interactive/docs/gallery/linechart
//
function getGDP2(){
    //The getJSON, when used without the $.(,function(){}); returns a more complicated data structure,that would require aditional parsing
    //https://api.jquery.com/jQuery.getJSON/

    var data_json_test=$.getJSON("http://localhost:8080/worldgdp/api/countries/"+code+"/gdp");
    console.log("data_json w/o function call");
    console.log(data_json_test['responseJSON']);
    
    $.getJSON("http://localhost:8080/worldgdp/api/countries/"+code+"/gdp",function(data_json){
    
    //to use the above reference, the web.xml of the Java projectworldgdp\src\main\webapp\static\WEB-INF\ (or Tomcat filters) must be edited
    //to include: Access-Control-Allow-Origin: *  
    //https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Origin
    //http://tomcat.apache.org/tomcat-7.0-doc/config/filter.html#CORS_Filter
    google.load("visualization", "1", {packages:["corechart"]});
    dataTable_json = new google.visualization.DataTable();
    

	dataTable_json.addColumn('number', 'Years');
	dataTable_json.addColumn('number', 'GDP (current US$):'+code);
    console.log("data_json=");
    console.log(data_json);
    

    //var rows_json=[];
		//console.log(numeral(646438380568.715).format('($0.00 a)'));
        
		/*data_json.each(data_json, function(item){
			rows_json.push([item.year, item.value])
		});*/
    $.each(data_json, function(item) {
        console.log("each item #=",item,", ",data_json[item]);
        console.log(data_json[item].year);
        console.log(data_json[item].value);
        rows_json.push([data_json[item].year,data_json[item].value]);
    
    });

    for (var obj in data_json){  //https://stackoverflow.com/questions/1078118/how-do-i-iterate-over-a-json-structure

        console.log("for loop obj"+data_json[obj]);
        console.log("for loop year,value"+data_json[obj].year+", "+data_json[obj].value);
        //rows_json.push([data_json[obj].year,data_json[obj].value]);
    }
    
		dataTable_json.addRows(rows_json);
		
		options = {
			hAxis: {
				title: 'Year (grid = 2)',
                gridlines:{count:5},
                textStyle:{bold:true,color:"#006644"}
			},
            isStacked:'relative',
			vAxis: {
				title: 'GDP (current US$)'
			},
            title: 'Multiple country ('+code+', '+code2+') Stacked (relative) Area GDP Chart w/ JSON data',
            height:900,
            width:900
	      };
		options2 = {
			hAxis: {
				title: 'Year (grid = 2)',
                gridlines:{count:5},
                textStyle:{bold:true,color:"#006644"}
			},
			vAxis: {
				title: 'GDP (current US$)',
                format:'Scientific'
			},
            title: 'Multiple country ('+code+', '+code2+') GDP Chart w/ JSON data',
            height:900,
            width:900
	      };
		optionsBar = {
			hAxis: {
				title: 'Years',
                //gridlines:{count:10},
                ticks: [2008,2010,2012,2014,2016,2018],
                textStyle:{bold:true,color:"#006644"},
                format:'####'
			},
			vAxis: {
				title: 'GDP (current US$)',
                format:'short'
			},
            trendlines: {
                0: {type: 'linear', lineWidth: 3, opacity: .3,showR2:true,visibleInLegend:true},
                1: {type: 'polynomial', degree:2, lineWidth: 3, opacity: .5,showR2:true,visibleInLegend:true}
                
                //1: {type: 'exponential', lineWidth: 10, opacity: .3}
            },
            legend:{textStyle:{fontSize:12}},
            title: 'Multiple country ('+code+', '+code2+') GDP Bar Chart w/ JSON data',
            height:900,
            width:900
	      };

	//Original call for .setOnLoadCallback was not being called properly without the $("#gdp-chart), so added google.setonloadcallback to earlier function. 
    data_global=dataTable_json;
    
    //var chart = new google.visualization.LineChart(document.getElementById('gdp-chart2'));
	//chart.draw(dataTable_json, options);   
        
    getGDP2b();
        
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
function getGDP2b(){
    $.getJSON("http://localhost:8080/worldgdp/api/countries/"+code2+"/gdp",function(data_json){
    google.load("visualization", "1", {packages:["corechart"]});
    dataTable_json2 = new google.visualization.DataTable();
	dataTable_json2.addColumn('number', 'Years');
	dataTable_json2.addColumn('number', 'GDP (current US$)');
    console.log("data_json=");
    console.log(data_json);
    
    $.each(data_json, function(item) {
        console.log("each item #=",item,", ",data_json[item]);
        console.log(data_json[item].year);
        console.log(data_json[item].value);
        rows_json2.push([data_json[item].year,data_json[item].value]);
    
    });

    
		dataTable_json2.addRows(rows_json2);
		



  
    getGDPjoin();
    
    });   //

    
  
}

function getGDPjoin(){
    console.log("data_json outside loop:",rows_json);
    console.log("data_json2 outside loop:",rows_json2);
    //https://developers.google.com/chart/interactive/docs/reference#google_visualization_data_join
    //https://developers.google.com/chart/interactive/docs/gallery/linechart
    var rows_joined=[];
    //
    dataTable_json_joined = new google.visualization.DataTable();

    if(rows_json.length==rows_json2.length){
        $.each(rows_json, function(item) {
            rows_joined.push([rows_json[item][0],rows_json[item][1],rows_json2[item][1]]);
        });
        dataTable_json_joined.addColumn('number', 'Years');
        dataTable_json_joined.addColumn('number', 'GDP ('+code+')');
        dataTable_json_joined.addColumn('number','GDP ('+code2+')');       
        dataTable_json_joined.addRows(rows_joined);
    }
    else{
        console.log("unable to join, different number of rows in each country GDP");
        dataTable_json_joined=dataTable_json;
    }
               
    
    console.log("rows joined:",rows_joined);
    //calling google.visualization.LineChart outside of internal loop results in error, unless this fn is called after setonloadcallback


    var chart = new google.visualization.ColumnChart(document.getElementById('gdp-chart'));
	chart.draw(dataTable_json_joined, optionsBar);   
    
    var chart2 = new google.visualization.AreaChart(document.getElementById('gdp-chart2'));
	chart2.draw(dataTable_json_joined, options2);   

    var chart3 = new google.visualization.AreaChart(document.getElementById('gdp-chart3'));
	chart3.draw(dataTable_json_joined, options); 

    
}


