(function($, $document, $window) {
    "use strict";

    console.log("extra lib");

    var facebookCheckbox = {
        className: ".cq-dialog-checkbox-showhide-facebook",
        nameCheckbox: "cqDialogCheckboxShowhideFacebookTarget"
    };
    var instagramCheckbox = {
        className: ".cq-dialog-checkbox-showhide-instagram",
        nameCheckbox: "cqDialogCheckboxShowhideInstagramTarget"
    };
    var linkedinCheckbox = {
        className: ".cq-dialog-checkbox-showhide-linkedin",
        nameCheckbox: "cqDialogCheckboxShowhideLinkedinTarget"
    };
    var twitterCheckbox = {
        className: ".cq-dialog-checkbox-showhide-twitter",
        nameCheckbox: "cqDialogCheckboxShowhideTwitterTarget"
    };
    var youtubeCheckbox = {
        className: ".cq-dialog-checkbox-showhide-youtube",
        nameCheckbox: "cqDialogCheckboxShowhideYoutubeTarget"
    };
    var githubCheckbox = {
        className: ".cq-dialog-checkbox-showhide-github",
        nameCheckbox: "cqDialogCheckboxShowhideGithubTarget"
    };

    var socialMedia = {
        facebookCheckbox,
        instagramCheckbox,
        linkedinCheckbox,
        twitterCheckbox,
        youtubeCheckbox,
        githubCheckbox
    };

    $(document).on("foundation-contentloaded", function(e) {
        // if there is already an inital value make sure the according target element becomes visible
        for (var props in socialMedia) {
            $(socialMedia[props].className).each(function() {
                checkboxShowHideHandler(
                    $(socialMedia[props].className, e.target),
                    socialMedia[props].nameCheckbox
                );
            });
        }

        // $(".cq-dialog-checkbox-showhide-instagram").each(function() {
        //     checkboxShowHideHandler(
        //         $(".cq-dialog-checkbox-showhide-instagram", e.target),
        //         instagramCheckbox
        //     );
        // });
    });


    $(document).on("change", socialMedia.facebookCheckbox.className, function(
        e
    ) {
        checkboxShowHideHandler(
            $(this),
            socialMedia.facebookCheckbox.nameCheckbox
        );
    });

    $(document).on("change", socialMedia.instagramCheckbox.className, function(
        e
    ) {
        checkboxShowHideHandler(
            $(this),
            socialMedia.instagramCheckbox.nameCheckbox
        );
    });

    $(document).on("change", socialMedia.linkedinCheckbox.className, function(
        e
    ) {
        checkboxShowHideHandler(
            $(this),
            socialMedia.linkedinCheckbox.nameCheckbox
        );
    });

    $(document).on("change", socialMedia.twitterCheckbox.className, function(
        e
    ) {
        checkboxShowHideHandler(
            $(this),
            socialMedia.twitterCheckbox.nameCheckbox
        );
    });

    
    $(document).on("change", socialMedia.youtubeCheckbox.className, function(
        e
    ) {
        checkboxShowHideHandler(
            $(this),
            socialMedia.youtubeCheckbox.nameCheckbox
        );
    });

    
    $(document).on("change", socialMedia.githubCheckbox.className, function(
        e
    ) {
        checkboxShowHideHandler(
            $(this),
            socialMedia.githubCheckbox.nameCheckbox
        );
    });

    function checkboxShowHideHandler(el, nameCheckbox) {
        el.each(function(i, element) {
            if ($(element).is("coral-checkbox")) {
                // handle Coral3 base drop-down
                Coral.commons.ready(element, function(component) {
                    showHide(component, element, nameCheckbox);
                    component.on("change", function() {
                        showHide(component, element, nameCheckbox);
                    });
                });
            } else {
                // handle Coral2 based drop-down
                var component = $(element).data("checkbox");
                if (component) {
                    showHide(component, element, nameCheckbox);
                }
            }
        });
    }

    function showHide(component, element, nameCheckbox) {
        // get the selector to find the target elements. its stored as data-.. attribute
        var target = $(element).data(nameCheckbox);
        var $target = $(target);

        if (target) {
            $target.addClass("hide");
            if (component.checked) {
                $target.removeClass("hide");
            }
        }
    }
})($, $(document), $(window));
