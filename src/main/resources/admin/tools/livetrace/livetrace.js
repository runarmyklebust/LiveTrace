


var mustache = require('/lib/xp/mustache');
var portal = require('/lib/xp/portal');

function handleGet(req) {

    portal.portalUrl
    log.info("Req: " + JSON.stringify(req, null, 2));
    var view = resolve('livetrace.html');
    var params = {
        launcherJsUrl: portal.assetUrl({path: "/js/launcher.js", application: "com.enonic.xp.admin.ui"})
    };
    return {
        contentType: 'text/html',
        body: mustache.render(view, params)
    };
}
exports.get = handleGet;