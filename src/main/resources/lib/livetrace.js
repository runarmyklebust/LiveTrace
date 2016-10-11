var bean = __.newBean('com.enonic.app.LiveTraceBean');

exports.getAll = function () {
    var result = bean.getAll();
    return __.toNativeObject(result);
};
