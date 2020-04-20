$(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
  // check elements that has attribute data-foundation-validation with value starting with "youtube-link"
  selector: "[data-foundation-validation^='youtube-link']",
  validate: function(el) {
    // parse the max number from the attribute value, the value maybe something like "youtube-link"
    var validationName = el.getAttribute("data-validation")

    if (isYoutubeLinkNotValid(el.value)){
      return "You link is not youtube link! Please, check the entered data!";
    }
  }
});

function isYoutubeLinkNotValid(youtubeLink){

  let regexp_watch = '^https:\/\/www.youtube.com\/watch\\?v=.+';
  let regexp_embed = '^https:\/\/www.youtube.com\/embed\/.+';

  return (youtubeLink.match(regexp_watch) == null & youtubeLink.match(regexp_embed) == null);
}