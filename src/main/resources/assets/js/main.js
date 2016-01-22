$(function () {

    updateTrace();
    setInterval(updateTrace, 1000);

});


function updateTrace() {

    $.ajax({
        dataType: 'json',
        url: SERVICE_URL,
        success: renderTrace
    });

}

function updateGraph(values, ids) {
    $('#container').highcharts({
        chart: {
            type: 'column',
            animation: false
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Time (ms)'
            }
        },
        xAxis: {
            min: 0,
            labels: {
                enabled: false
            },
            categories: ids
        },
        series: [{
            name: 'Request',
            data: values,
            animation: false,
            events: {
                click: showData
            }
        }],
        legend: {
            enabled: false
        },
        title : {
            text: null
        }
    });
}

function showData(event) {
    alert("ID" + event.point.category);
}

function renderTrace(data) {

    var values = [];
    var ids = [];

    for (var i = 0; i < data.max; i++) {
        values[i] = 0;
        ids[i] = 0;
    }

    data.entries.forEach(function (value, index) {
        values[index] = value.time;
        ids[index] = value.id;
    });

    updateGraph(values, ids);

}

function clickDetail(id) {
    $.ajax({
        dataType: 'json',
        url: SERVICE_URL + '?' + id,
        success: updateDetail
    });
}

function updateDetail(data) {
    var html = '<table>';
    html += '<tr><td>Id:<td><td>' + data.id + '</td></tr>';
    html += '<tr><td>Date:</td><td>' + data.requestTime + '</td>';
    html += '<tr><td>Time:<td><td>' + data.time + ' ms</td></tr>';
    html += '<tr><td>Completed:<td><td>' + data.completed + '</td></tr>';
    html += '<tr><td>Url:<td><td>' + data.url + '</td></tr>';
    html += '<tr><td>Site:<td><td>' + data.site + '</td></tr>';
    html += '<tr><td>Content:<td><td>' + data.content + '</td></tr>';
    html += '</table>';

    $('#detail').html(html);
}
