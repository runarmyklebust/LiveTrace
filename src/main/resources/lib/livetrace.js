var bean = __.newBean('com.enonic.app.LiveTraceBean');

exports.getAll = function () {
    var result = bean.getAll();
    return __.toNativeObject(result);
};

exports.getEntry = function (id) {
    var result = bean.getEntry(id);
    return __.toNativeObject(result);
};
