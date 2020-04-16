$(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
  // check elements that has attribute data-foundation-validation with value starting with "multifield-max"
  selector: "[data-foundation-validation^='custom-multifield-max']",
  validate: function(el) {
    // parse the max number from the attribute value, the value maybe something like "multifield-max-6"
    var validationName = el.getAttribute("data-validation")
    var max = validationName.replace("custom-multifield-max-", "");
    max = parseInt(max);
    let addButton = el.querySelector('button[coral-multifield-add]');
    // variant with disable button
    if (el.items.length >= max) {
      addButton.setAttribute('disabled','')
    } else if (el.items.length < max || addButton.hasAttribute('disabled')) {
      addButton.removeAttribute('disabled');
    }

    // variant with alert 

    // if (el.items.length > max){

    //     // items added are more than allowed, return error
    //     var ui = $(window).adaptTo("foundation-ui");
    //      var item = el.items.last();
    //      var result = item.remove();
    //      ui.alert("Warning", "Maximum " + max + " items!", "notice");
    //     return "Max allowed items is "+ max
    // }
  }
});