$(window)
    .adaptTo("foundation-registry")
    .register("foundation.validation.validator", {
        selector: "[data-foundation-validation^='icon-number-field']",
        validate: function (el) {
            if (isIconValueNotValid(el.value)) {
                return "You value is not icon class! Please, check the entered data!";
            }
        },
    });

function isIconValueNotValid(iconNumber) {
    let regexp_watch = /^fa[rbs]\s+fa-.+/;

    return iconNumber.match(regexp_watch) == null;
}