$(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
    selector: "[data-foundation-validation^='icon-class-field']",
    validate: function (el) {
        if (isIconNotNeed(el.value)){
            return
        } else if (isIconValueWithHtmlTag(el.value)) {
            el.value = cutIconClassFromHtmlTag(el.value);
        } else if (isIconValueNotValid(el.value)) {
            return "You value is not icon class! Please, check the entered data!";
        }
    },
});

function isIconNotNeed(iconClass){
    return iconClass.trim() === "none";
}

function isIconValueNotValid(iconClass) {
    let regexp_watch = /^fa[rbs]\s+fa-.+/;
    return iconClass.match(regexp_watch) == null;
}

function isIconValueWithHtmlTag(iconClass) {
    let regexp_watch = /<i class=\"fa[rbs]\s+fa-.+\"><\/i>/;
    return iconClass.match(regexp_watch) != null;
}

function cutIconClassFromHtmlTag(iconClass) {
    let regexp_watch = /fa[rbs]\s+fa-.+\"/;
    let result = iconClass.match(regexp_watch);
    return result[0].substring(0,(result[0].length - 1))
}