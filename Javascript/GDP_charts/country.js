var pageSize = 10;
//var code = [[${c.code}]];
var code = "IND";
(function(){
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(getGDP);
	
    //getGDP();
	//referring to getGDP() too early (before google.setOnLoadCallback) may result in error.

})();

//https://www.w3schools.com/howto/howto_google_charts.asp
function getGDP(){

    google.load("visualization", "1", {packages:["corechart"]});
		var dataTable = new google.visualization.DataTable();
		dataTable.addColumn('number', 'Years');
		dataTable.addColumn('number', 'GDP (current US$)');
		var rows = [];
		//console.log(numeral(646438380568.715).format('($0.00 a)'));
        
		/*_.each(data, function(item){
			rows.push([item.year, item.value])
		});*/
        rows.push([2008,7000]);
        rows.push([2018,10000]);
		dataTable.addRows(rows);
		
		
		var options = {
			hAxis: {
				title: 'Year (grid = 2)',
                gridlines:{count:5},
                textStyle:{bold:true,color:"#006644"}
			},
			vAxis: {
				title: 'GDP (current US$)'
			}
	      };

	//Original call for .setOnLoadCallback was not being called properly without the $("#gdp-chart), so added google.setonloadcallback to earlier function. 
    
    var chart = new google.visualization.LineChart(document.getElementById('gdp-chart'));
	chart.draw(dataTable, options);   
    
    /*
    $("#gdp-chart").html('');   //https://www.w3schools.com/jquery/html_html.asp
    //this jquery will set remove the html within this tag.  
    //doesn't seem to be necessary as the "Loading..." dissapears without this statement 
	google.charts.setOnLoadCallback(function(){
			var chart = new google.visualization.LineChart(document.getElementById('gdp-chart'));
			chart.draw(dataTable, options);
		});
	*/
    
    
	//});   //without using jQUERY getJSON() method https://www.w3schools.com/jquery/ajax_getjson.asp
}




