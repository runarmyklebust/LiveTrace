var nodeLib = require('/lib/xp/node');
var contextLib = require('/lib/xp/context');

exports.get = function (req) {

    var result = contextLib.run({
        branch: 'master',
        repository: 'live-trace',
        user: {
            login: 'su',
            userStore: 'system'
        },
        principals: ["role:system.admin"]
    }, getAllData);


    log.info("Result: %s", result);

    if (!result) {
        return {
            status: 404
        };
    }

    return {
        status: 200,
        contentType: 'application/json',
        body: result
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
