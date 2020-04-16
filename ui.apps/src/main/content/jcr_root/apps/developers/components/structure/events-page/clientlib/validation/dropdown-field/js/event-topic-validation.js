var eventTopicSelector = "[data-foundation-validation^='event-topic-field']";
var eventTypeSelector = "[data-foundation-validation^='event-type-field']";

$(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
  selector: eventTopicSelector,
  validate: function (el) {

    let isUnique = true;

    $("input".concat(eventTopicSelector)).each(function (indexInArray, topicInput) {
      if (topicInput.id != el.id && isTopicEquals(topicInput.value, el.value)) {
        isUnique = false;
        return;
      }
    });

    if (!isUnique) {
      return "Topics must be unique! Please, check the entered data!";
    }
  }
});

$(window).adaptTo("foundation-registry").register("foundation.validation.validator", {
  selector: eventTypeSelector,
  validate: function (el) {

    let isUnique = true;

    $("input".concat(eventTypeSelector)).each(function (indexInArray, typeInput) {
      if (typeInput.id != el.id && isTopicEquals(typeInput.value, el.value)) {
        isUnique = false;
        return;
      }
    });

    if (!isUnique) {
      return "Type must be unique! Please, check the entered data!";
    }

  }
});

function isTopicEquals(str1, str2) {
  return (str1.trim().toUpperCase() === str2.trim().toUpperCase())
}
