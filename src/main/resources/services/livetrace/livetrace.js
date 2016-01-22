var traceLib = require('/lib/livetrace');

exports.get = function (req) {
    var id = req.params.id;
    var result;

    if (id) {
        result = traceLib.getEntry(id);
    } else {
        result = traceLib.getAll();
    }

    return {
        status: 200,
        contentType: 'application/json',
        body: result
    };
};
