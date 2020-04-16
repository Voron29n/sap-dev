var textInput = {
    classWithSimpleText: ".simple-text",
    classWithDisableText: ".disable-text",
};

(function (document, $) {
    $(document).on("foundation-contentloaded", function (e) {
        let inputVal = $(textInput.classWithSimpleText).val();
        changeInputValue(inputVal);
    });
})(document, Granite.$);

$(window)
    .adaptTo("foundation-registry")
    .register("foundation.validation.validator", {
        // check elements that has attribute data-foundation-validation with value starting with "youtube-link"
        selector: "[data-foundation-validation^='validation-text']",
        validate: function (el) {
            changeInputValue(el.value);
        },
    });

function changeInputValue(val) {
    let valueInput = val.toLowerCase().trim().replace(/\s+/g, '-');
    $(textInput.classWithDisableText).val(valueInput);
}
