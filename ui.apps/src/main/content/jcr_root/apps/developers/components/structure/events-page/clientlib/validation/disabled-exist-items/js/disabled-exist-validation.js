(function (window, document, $, Granite, Coral) {
    "use strict";

    Coral.commons.ready($("coral-multifield-item"), function (e) {
        disabledItemsFields(true);
    });

    $("#shell-propertiespage-doneactivator").click(function () {
        disabledItemsFields(false);
    });
})(window, document, Granite.$, Granite, Coral);

function disabledItemsFields(isDisable) {
    $(".coral-multifield-exist-disabled coral-multifield-item").each(function (
        indexInArray,
        element
    ) {
        arrayWithFieldAndButtom(element).forEach((item) => {
            item != null && isDisable
                ? item.setAttribute("disabled", "")
                : item.removeAttribute("disabled", "");
        });
    });
}

function arrayWithFieldAndButtom(element) {
    return [
        element.querySelector(
            "input[data-foundation-validation^='event-field-']"
        ),
        element.querySelector("button.coral3-Multifield-remove"),
    ];
}
