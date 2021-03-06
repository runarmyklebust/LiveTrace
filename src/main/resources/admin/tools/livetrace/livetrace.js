var thymeleaf = require('/lib/xp/thymeleaf');
var portal = require('/lib/xp/portal');
var traceLib = require('/lib/livetrace');
var repoLib = require('/lib/xp/repo');
var nodeLib = require('/lib/xp/node');
var contextLib = require('/lib/xp/context');

exports.get = function (req) {

    var view = resolve('livetrace.html');

    var result = contextLib.run({
        branch: 'master',
        repository: 'live-trace',
        user: {
            login: 'su',
            userStore: 'system'
        },
        principals: ["role:system.admin"]
    }, getAllData);
    
    var model = {
        launcherJsUrl: portal.assetUrl({path: "/js/launcher.js", application: "com.enonic.xp.admin.ui"}),
        jsUrl: portal.assetUrl({path: "/js/traceTracer.js"}),
        cssUrl: portal.assetUrl({path: "/css/main.css"}),
        tableSorterUrl: portal.assetUrl({path: "/js/jquery.tablesorter.min.js"}),
        tableSorterCss: portal.assetUrl({path: "/css/theme.black-ice.css"}),
        data: result
    };

    model.serviceUrl = portal.serviceUrl({
        service: 'livetrace-service'
    });

    return {
        contentType: 'text/html',
        body: thymeleaf.render(view, model)
    };
};


var getAllData = function () {
    var result = nodeLib.query({
        start: 0,
        count: 10,
        sort: "duration DESC",
        aggregations: {
            urls: {
                terms: {
                    field: "url",
                    order: "_count desc",
                    size: 100
                },
                aggregations: {
                    duration: {
                        histogram: {
                            field: "duration",
                            interval: 100,
                            minDocCount: 1,
                            extendedBoundMin: 0,
                            extendedBoundMax: 10000,
                            order: "_key desc"
                        }
                    },
                    durationStats: {
                        stats: {
                            field: "duration"
                        }
                    }
                }
            }
        }
    });
    return result;
};
