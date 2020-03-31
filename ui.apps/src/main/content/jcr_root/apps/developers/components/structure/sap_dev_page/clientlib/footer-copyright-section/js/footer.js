$(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
  // check elements that has attribute data-foundation-validation with value starting with "multifield-max"
  selector: "[data-foundation-validation^='multifield-max']",
  validate: function(el) {
    // parse the max number from the attribute value, the value maybe something like "multifield-max-6"
    var validationName = el.getAttribute("data-validation")
    var max = validationName.replace("multifield-max-", "");
    max = parseInt(max);
    // el here is a coral-multifield element
    // see: https://helpx.adobe.com/experience-manager/6-4/sites/developing/using/reference-materials/coral-ui/coralui3/Coral.Multifield.html
    if (el.items.length > max){
        // items added are more than allowed, return error
        var ui = $(window).adaptTo("foundation-ui");
         console.log(el.items);
         var item = el.items.last();
         var result = item.remove();
         ui.alert("Warning", "Maximum " + max + " links are allowed!", "notice");
        return "Max allowed items is "+ max
    }
  }
});