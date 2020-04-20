$(window)
    .adaptTo("foundation-registry")
    .register("foundation.validation.validator", {
        selector: "[data-foundation-validation^='event-field']",
        validate: function (el) {
            let validationName = el.getAttribute("data-validation");
            let validationTitle = validationName.replace("event-field-", "");

            let isUnique = true;

            $(getInputSelector(validationName)).each(function (
                indexInArray,
                topicInput
            ) {
                if (
                    topicInput.id != el.id &&
                    isValueEquals(topicInput.value, el.value)
                ) {
                    isUnique = false;
                    return;
                }
            });

            if (!isUnique) {
                return (
                    ucFirst(validationTitle) +
                    " must be unique! Please, check the entered data!"
                );
            }
        },
    });

function getInputSelector(validationName) {
    if (!validationName) {
        return validationName;
    }
    let validationSelector = "input[data-foundation-validation^='";
    return validationSelector.concat(validationName, "']");
}

function ucFirst(validationTitle) {
    if (!validationTitle) {
        return validationTitle;
    }
    return validationTitle[0].toUpperCase() + validationTitle.slice(1);
}

function isValueEquals(str1, str2) {
    return str1.trim().toUpperCase() === str2.trim().toUpperCase();
}
