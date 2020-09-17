function deleteObject(objectName, id, url, ctx){
	if(confirm("Are you sure you want to delete this "+objectName)){
		window.location.href = ctx + url + id;
	} else {
		return false;
	}
}


function generateTable(headerArr , columnArr, noOfRows){
	var tableDiv = $("#tableDiv");
	var table = null;
	if($("#dynaTable").attr("id") == null){
		// Creating a new table
		table = $("<table style='width:100%'/>");

		// adding id attribute to table
		table.attr("id", "dynaTable");

		// adding table in the div
		table.appendTo(tableDiv);
		
		
		/* for creating header <th> element - Only for the first time */
		for(var i in headerArr){
			$("<th/>").html(headerArr[i]).appendTo(table);
		}
	} else {
		// getting already created table object
		table = $("#dynaTable");
	}

	// creating a new tr
	for(var j=0; j<noOfRows; j++){
		var tr = $("<tr/>");
		var counter = $("#counterelem").val();
		counter++;
		$("#counterelem").val(counter);
		for(var i in columnArr){
			// creating a new td
			// .html(...) function is use to add html content inside td
			// this function can take any simple text as well as html code
			// for ex. if a text box need to be added then .html("<input type='text' value='someValue' name='emlementName' id='elementId'>")
			var valueArr = columnArr[i].split(":");
			var td = $("<td/>");
			var elemvalue = "";
			var elemValueArr = null;
			var val = '';
			if(valueArr[1].indexOf(",") != -1){
				elemValueArr = valueArr[1].split(",");
				if(elemValueArr != null)
					val = elemValueArr[1];
				else 
					val = valueArr[1];
				if(elemValueArr == null){
					elemValueArr = new Array();
					elemValueArr[0] = val;
				}
			}
			if(elemValueArr == null){
				elemValueArr = new Array();
				elemValueArr[0] = valueArr[1];
			}
			switch(valueArr[0]){
				case 'label':
					td.html("<input type='hidden' id='headId"+counter+"' name='headId"+counter+"' value='"+elemValueArr[0]+"'><label>"+val+"  </label>" ).appendTo(tr);
				break;
				case 'text':
					if(elemValueArr[1] == null || elemValueArr == "")
						elemvalue = "0.0";
					else
						elemvalue = elemValueArr[1];
					td.html("<input type='hidden' id='"+elemValueArr[0]+counter+"' name='"+elemValueArr[0]+counter+"' value='"+elemvalue+"'><input type='text' name='"+elemValueArr[0]+"' id='"+elemValueArr[0]+"' value='"+elemvalue+"' size='15' onkeyup='javascript:populateHiddenValue(\""+elemValueArr[0]+counter+"\", this.value, \"text\")'> ").appendTo(tr);
				break;
				case 'hidden':
					td.html("<input type='text' name='"+valueArr[1]+"' id='"+valueArr[1]+"' value='"+valueArr[1]+"' size='10'>").appendTo(tr);
				break;
				case 'radio':
					td.html("<input type='radio' name='"+valueArr[1]+"' id='"+valueArr[1]+"' value='"+valueArr[1]+"' size='10'>").appendTo(tr);
				break;
				case 'checkbox':
					if(elemValueArr[1] == null || elemValueArr == "")
						elemValue = "false";
					else
						elemValue = elemValueArr[1];
					var innerHtml = "<input type='hidden' id='"+elemValueArr[0]+counter+"' name='"+elemValueArr[0]+counter+"' value='"+elemValue+"'><input type='checkbox' name='"+elemValueArr[0]+counter+"' id='check"+elemValueArr[0]+counter+"' onclick='populateHiddenValue(\""+elemValueArr[0]+counter+"\", this.value, \"checkbox\")'";
					if(elemValue == 'true')
						innerHtml = innerHtml + " checked ";
					
					innerHtml = innerHtml +">";
					td.html(innerHtml);
					td.appendTo(tr);
				break;
				case 'textarea':
					td.html("<textarea name='"+valueArr[1]+"' id='"+valueArr[1]+"' value='"+valueArr[1]+" style='width:10%' rows='3' cols='3' size='10'></textarea>").appendTo(tr);
				break;
				case 'href':
					var link = valueArr[1].split(",");
					td.html("<a name='"+valueArr[1]+"' id='"+valueArr[1]+"' href='"+link[0]+"' rows='3' cols='3'>"+link[0]+" size='10' </a>").appendTo(tr);
				break;
				case 'image':
					td.html("<img SRC='"+valueArr[1]+"'>").appendTo(tr);
				break;
			
			}
		}
		tr.appendTo(table);
	}
	table.attr("border","1");
	
	/* to add css class use $("#dynaTable").attr("class", "<value>") */
	/* to add style use $("#dynaTable").css("<css>:<cssVal>, <css>:<cssVal>, ... ") */
	/* to add any more html element inside any TD use .append() method. Also .appendTo() is useful. Study about it before using */
	/* */

}




function populateHiddenValue(id, value, type){
	//alert('id: '+id+' -- value: '+value);
	if(type == 'text')
		$("#"+id).val(value);
	if(type == 'checkbox'){
	//	alert($("#check"+id).is(':checked'));
		if($("#check"+id).is(':checked'))
			$("#"+id).val('true');
		else
			$("#"+id).val('false');
	}
		
}

/* Menu Javascript Start */
function IEHoverPseudo() {

    var navItems = document.getElementById("primary-nav").getElementsByTagName("li");

    for (var i=0; i<navItems.length; i++) {
        if(navItems[i].className == "menubar") {
            navItems[i].onmouseover=function() { this.className += " over"; }
            navItems[i].onmouseout=function() { this.className = "menubar"; }
        }
    }

}
/*window.onload = IEHoverPseudo;*/
/* Menu javascript end */
/* for calendar */

function datePick(elemIds){
	for(var i=0; i<elemIds.length; i++){
		$("#"+elemIds[i]).attr("readonly",true);
		$("#"+elemIds[i]).datepicker({
	        yearRange: "-30:+10",dateFormat: 'dd/mm/yy',showOn: "button",changeMonth: true,changeYear: true,
			buttonImage:""+appctx+"/images/index.jpeg" ,buttonImageOnly: true ,buttonText: "",autoSize:true ,
			showButtonPanel: true, closeText: 'Clear',
			beforeShow: function( input ) {
		        setTimeout(function() {
		        var clearButton = $(input )
		            .datepicker( "widget" )
		            .find( ".ui-datepicker-close" );
		        clearButton.unbind("click").bind("click",function(){$.datepicker._clearDate( input );});
		        }, 1 );
		    }
			});
	}
}