var mustache = require('/lib/xp/mustache');
var portal = require('/lib/xp/portal');

exports.get = function (req) {

    var view = resolve('livetrace.html');

    var model = {
        launcherJsUrl: portal.assetUrl({path: "/js/launcher.js", application: "com.enonic.xp.admin.ui"}),
        jsUrl: portal.assetUrl({path: "/js/main.js"}),
        cssUrl: portal.assetUrl({path: "/css/main.css"})
    };

    model.serviceUrl = portal.serviceUrl({
        service: 'livetrace'
    });

    return {
        contentType: 'text/html',
        body: mustache.render(view, model)
    };
};
