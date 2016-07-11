/**
 * write by gavin
 * 转换函数
 * appendColumns : table的 <th>标签集合</th>
 * appendItemColumnValues : table的 <th></th> tabs collection array
 * */
function convertGHTable2ViewTable(data, hiddenColumns, appendColumns, appendItemColumnValues){
	//
	var columns = data.columns;
	var items = data.items;
	var columnsHTMData = "";
	var itemsHTMDataCollection = "";
	
	columnsHTMData += "<thead><tr>";
	for (var i = 0; i < columns.length; i++){
		columnsHTMData += "<th nowrap=\"nowrap\" ";
		if(hiddenColumns.indexOf(columns[i].coumnName) >= 0){
			columnsHTMData += " hidden=\"hidden\" ";
		}
		columnsHTMData += ">";
		columnsHTMData += columns[i].coumnName;
		columnsHTMData += "</th>";
	}
	
	if(appendColumns != undefined && appendColumns != ""){
		/*for (var i = 0; i < appendColumns.length; i++){
				columnsHTMData += "<th nowrap=\"nowrap\">" + appendColumns[i] + "</th>";
		}*/
		columnsHTMData += appendColumns;
	}
	columnsHTMData += "</thead>";
		
	for ( var itemKey in items) {
		var itemsHTMData = "<tr>";
		var fieldValues = items[itemKey].fields;
		for (var i = 0; i < columns.length; i++) {
			itemsHTMData += "<th ";
			if(hiddenColumns.indexOf(columns[i].coumnName) >= 0){
				itemsHTMData += " hidden=\"hidden\" ";
			}
			itemsHTMData += ">";
			itemsHTMData += fieldValues[columns[i].coumnName];
			itemsHTMData += "</th>";
		}
		
		if(appendItemColumnValues != undefined && appendItemColumnValues != ""){
			itemsHTMData += appendItemColumnValues[itemKey];
		}
		
		itemsHTMData += "</tr>";
		itemsHTMDataCollection += itemsHTMData;
	}
	
	return columnsHTMData + itemsHTMDataCollection;
}

/**
 * delet item from array,
 * array,key is type of int index 
 */
function deleteItemFromArray(array, key){
	
	for(var i = 0; i < array.length; ++ i){
		if(array[i].coumnName == key){
			delete array[i];
			array.length -= 1;
		}
	}
}