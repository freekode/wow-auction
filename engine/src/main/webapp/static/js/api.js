/**
 * Класс для работы с rest api, содержит все требуемые пути и методы для обращения к ним
 * @constructor
 */
var API = function () {
    /**
     * Контекст
     */
    var ROOT = '/wow-auction';

    /**
     * Список доступных url
     */
    var resources = {
        snapshots24h: {
            url: '/api/snapshot/24h/{realmId}',
            type: 'get'
        },
        getItemList: {
            url: '/api/item/list/{page}/{amount}',
            type: 'get'
        }
    };


    function API() {
        // constructor
    }


    /**
     * Внутренний метод для обычного json запроса
     * @param resource ресурс из списка
     * @param data данные для передачи
     * @private
     */
    var _send = function (resource, data) {
        var config = {
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            dataType: "json"
        };
        $.extend(config, resource);

        config.url = ROOT + config.url;

        return _request(config);
    };

    /**
     * Внутренний метод для загрузки файла
     * @param resource ресурс из списка
     * @param data данные для загрузки на сервер
     * @private
     */
    var _upload = function (resource, data) {
        var config = {
            data: data,
            cache: false,
            contentType: false,
            processData: false
        };
        $.extend(config, resource);

        return _request(config);
    };

    /**
     * Внутренний метод для вызова ajax
     * @param config конфиг для вызова
     * @returns возвращает promises
     * @private
     */
    var _request = function (config) {
        var dfd = $.Deferred();

        $.ajax(config).done(function (response) {
            if (response.success == false) {
                console.error(response);
                dfd.reject(response);
                return;
            }

            dfd.resolve(response.data);
        }).fail(function (response) {
            console.error(response);
            dfd.reject(response)
        });

        return dfd.promise();
    };

    /**
     * Преобразовывает url в строку для запросов вида /api/{id}
     * @param resource ресурс
     * @param values мап значений
     * @private
     */
    var _makeUrl = function (resource, values) {
        var params = resource.url.match(/{(\w+)}/gi);

        params.map(function (elem) {
            var paramName = elem.replace('{', '').replace('}', '');
            resource.url = resource.url.replace(elem, values[paramName])
        });

        return resource
    };


    /**
     * Возвращает снепшоты за 24 часа для реалма
     * @param param объект с id реалма
     */
    this.getSnapshots24h = function (param) {
        return _send(_makeUrl(resources.snapshots24h, param));
    };

    /**
     * Возвращает список айтемов
     * @param param страница и количество айтемов на странице
     */
    this.getItemList = function (param) {
        return _send(_makeUrl(resources.getItemList, param));
    }
};
