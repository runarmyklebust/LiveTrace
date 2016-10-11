$(function () {

    $("#summaryTable").tablesorter({
        theme: 'green'
    });

    updateTrace();
    
    //setInterval(updateTrace, 5000);

});

function updateTrace() {


    $.ajax({
        dataType: 'json',
        url: SERVICE_URL,
        success: renderTrace
    });

}

function renderTrace(data) {

    var html = '';

    if (typeof data.results !== 'undefined' && data.results.length > 0) {
        data.results.forEach(function addTrace(result) {
            html += addRow(result);
        });
    } else {
        html += addInitial();
    }

    html += '</table>';

    $("#summaryTable tbody").html(html);

    $("#summaryTable").trigger("update");

    setTimeout(function () {
        $("#summaryTable").trigger("sorton", [$("#summaryTable")[0].config.sortList]);
    }, 10)
}

function addInitial() {
    var html = '<tr>'
    html += '<td>No data</td>';
    html += '<td>0</td>';
    html += '<td>0</td>';
    html += '<td>0</td>';
    html += '</tr>';

    return html;
}


function addRow(data) {

    var html = '<tr>'
    html += '<td>' + data.key + '</td>';
    html += '<td>' + data.max + '</td>';
    html += '<td>' + data.count + '</td>';
    html += '<td>' + data.total + '</td>';
    html += '</tr>';

    return html;
}
