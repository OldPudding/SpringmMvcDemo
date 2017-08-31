$(function() {
	$('#dg1').datagrid({
		singleSelect : false,
		autoRowHeight : false,
		fitColumns : true,
		pagination : false,
		loadFilter : pagerFilter
	}).datagrid('loadData', getData());
});

function doSearch(value, name) {
	alert('You input: ' + value + '(' + name + ')');
}

function getData() {
	var rows = [];

	rows.push({
		inv : '<input type = "text" value="' + '文摘111' + '">',
		cz : "修改"+"&nbsp;&nbsp;&nbsp;"+"删除",
	});
	return rows;
}

function pagerFilter(data) {
	if (typeof data.length == 'number' && typeof data.splice == 'function') { // is
																				// array
		data = {
			total : data.length,
			rows : data
		}
	}
	var dg1 = $(this);
	var opts = dg1.datagrid('options');
	var pager = dg1.datagrid('getPager');
	$('#pp').pagination({
		total : 80,
		onSelectPage : function(pageNum, pageSize) {
			opts.pageNumber = pageNum;
			opts.pageSize = pageSize;
			$('#pp').pagination('refresh', {
				pageNumber : pageNum,
				pageSize : pageSize
			});
			dg1.datagrid('loadData', data);
		}
	});
	if (!data.originalRows) {
		data.originalRows = (data.rows);
	}
	var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
	var end = start + parseInt(opts.pageSize);
	data.rows = (data.originalRows.slice(start, end));
	return data;
}
