$(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
  // check elements that has attribute data-foundation-validation with value starting with "multifield-max"
  selector: "[data-foundation-validation^='footer-multifield-topic-max']",
  validate: function(el) {
    // parse the max number from the attribute value, the value maybe something like "multifield-max-6"
    let validationName = el.getAttribute("data-validation")
    let max = validationName.replace("footer-multifield-topic-max-", "");
    max = parseInt(max);

    let addButton = $(el).children('button[coral-multifield-add]')[0];

    if (el.items.length >= max) {
      addButton.setAttribute('disabled','')
    } else if (el.items.length < max && addButton.hasAttribute('disabled')) {
      addButton.removeAttribute('disabled');
    }
  }
});