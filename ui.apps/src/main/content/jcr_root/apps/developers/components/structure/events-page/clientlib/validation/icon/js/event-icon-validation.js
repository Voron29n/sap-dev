$(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
  // check elements that has attribute data-foundation-validation with value starting with "youtube-link"
  selector: "[data-foundation-validation^='icon-number-field']",
  validate: function(el) {
    // el here is a coral-multifield element

    if (isIconValueNotValid(el.value)){
      return "You link is not icon number! Please, check the entered data!";
    }
  }
});

function isIconValueNotValid(iconNumber){

  let regexp_watch = /^\\f[0-2][0-9a-f][0-9a-e]/;

  return (iconNumber.match(regexp_watch) == null);
}