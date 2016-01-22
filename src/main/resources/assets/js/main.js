$(function () {

    setInterval(updateTrace, 1000);

});


function updateTrace() {

    $.ajax({
        dataType: 'json',
        url: SERVICE_URL,
        success: renderTrace
    });

}

function updateGraph(values) {
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
        series: [{
            name: 'Request',
            data: values,
            animation: false

        }]
    });
}

function renderTrace(data) {

    var values = [];
    data.entries.forEach(function (value, index) {
        values[index] = value.time;
    });

    updateGraph(values);

}
