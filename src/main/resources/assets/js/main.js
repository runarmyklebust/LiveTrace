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
    $.ajax({
        dataType: 'json',
        url: SERVICE_URL + '?id=' + event.point.category,
        success: updateDetail
    });
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

function updateDetail(data) {
    var html = '<table width="100%">';
    html += addRow('Id', data.id);
    html += addRow('Date', data.requestTime);
    html += addRow('Time', data.time + " ms");
    html += addRow('Completed', data.completed);
    html += addRow('Url', data.url);

    if (data.site) {
        html += addRow('Site', data.site);
    }

    if (data.content) {
        html += addRow('Content', data.content);
    }

    html += '</table>';

    $('#detail').html(html);
}

function addRow(name, value) {
    return '<tr><td width="20%" style="padding-right: 10px; a"><b>' + name + '</b></td><td width="80%">' + value + '</td></tr>';
}
