Vue.component("view-by", {
    props: {
        servletUrl: String,
    },
    data() {
        return {
            isTypeSelect: true,
        };
    },
    methods: {
        byType: function () {
            this.isTypeSelect = true;
            this.$root.isEventTypeSelect = true;
        },
        byTopic: function () {
            this.isTypeSelect = false;
            this.$root.isEventTypeSelect = false;
        },
    },
    created: function () {
        let servletSelector = ".data";
        let servletExtension = ".json";
        this.$root.servletUrl = this.$props.servletUrl.concat(
            servletSelector,
            servletExtension
        );
        this.$root.updateAfterServletUrlExist();
    },
    template: 
        '<div class="navbar">' +
        '<div class="navbar-inner">' +
        '<span class="brand">View By:</span>' +
        '<ul class="nav">' +
        '<li :class="{ active: isTypeSelect }"><span @click="byType">Type</span></li>' +
        '<li :class="{ active: !isTypeSelect }"><span @click="byTopic">Topic</span></li>' +
        '</ul>' +
        '</div>' +
        '</div>',
});

Vue.component("event-listing", {
    props: {
        eventList: Array,
    },
    computed: {
        gridStyle() {
            return {
                gridTemplateColumns: `repeat(${this.eventList.length}, 1fr)`,
            };
        },
    },
    template:
        '<div class="events-listing-columns" :style="gridStyle">' +
        '<div class="item-events-column" v-for="eventColumn in eventList">' +
        '<h3>{{eventColumn.title}} <i v-if="!eventColumn.type" :class="[eventColumn.iconClass]"></i></h3>' +
        '<event-column :columnEvents="eventColumn.eventList"'+
                      ':numColumn="eventColumn.numColumn"' +
                      ':isType="eventColumn.type"' + 
                      ':title="eventColumn.title"' +
                      ':rowSize="eventColumn.rowSize"></event-column>' +
        '</div>' +
        '</div>',
});

Vue.component("event-column", {
    props: {
        columnEvents: Array,
        numColumn: Number,
        isType: Boolean,
        title: String,
        rowSize: Number
    },
    data() {
        return {
            activeColumn: 0,
        };
    },
    methods: {
        getArrayForColumn: function () {
            let array = [];
            let i = 0;
            while (i < this.numColumn) {
                let columnCountObj = {
                    isActive: i == this.activeColumn,
                    numCol: i++,
                };
                array.push(columnCountObj);
            }
            return array;
        },
        selectColumn: function (numColumn) {
            this.activeColumn = numColumn;
            let byParam = this.$root.getByParam(this.$props.isType);
            this.updateColumn(byParam, this.$props.title, numColumn);
        },
        updateColumn(byParam, title, numColumn) {
            this.$http
                .get(this.$root.servletUrl, {
                    params: { by: byParam, title: title, column: numColumn },
                    headers: {
                        "Content-Type": "application/json",
                        Accept: "application/json",
                    },
                })
                .then((response) =>
                    response.json().then((data) => {
                        let listResultQuery = [];
                        if (data.length == 0) {
                            return;
                        }
                        data.forEach((element) => {
                            listResultQuery.push(element);
                        });
                        this.$props.columnEvents = listResultQuery;
                    })
                );
        },
    },
    computed: {
        columnArray() {
            return this.getArrayForColumn();
        },
        columnStyle(){
          return {
            height: `${this.rowSize * 90}px`,
          }
        }
    },
    template:
        `<ul :class="[isType ? 'icons' : 'unstyled']" :style="columnStyle">` +
        '<li v-for="event in columnEvents">' +
        '<event :event="event" :isType="isType"></event>' +
        '</li>' +
        '<ul v-if="numColumn > 1" class="switch-column">' +
        '<li v-for="item in columnArray">' +
        '<span @click="selectColumn(item.numCol)">' +
        `<i :class="[item.isActive ? 'fas' : 'far', 'fa-circle']"></i></span>` +
        '</li>' +
        '</ul>' +
        '</ul>',
});


Vue.component("event", {
  props: {
      event: Object,
      isType: Boolean,
  },
  template:
      '<div>' +
      '<i v-if="isType" :class="[event.eventTopicIconClass]"></i>' +
      '<span class="date">{{event.eventDateStr}}</span>' +
      '<h4 v-html="event.eventTitle"></h4>' +
      '<p>{{event.eventDescription}}</p>' +
     '</div>',
});

var app = new Vue({
    el: "#events-listing",
    data: {
        servletUrl: "",
        emptyMessage: "",
        isEventTypeSelect: true,
        eventList: [],
    },
    watch: {
        isEventTypeSelect: function (newVal, oldVal) {
          this.updateAllColumns(this.getByParam(newVal));
        },
    },
    methods: {
        updateAfterServletUrlExist(){
          this.updateAllColumns(this.getByParam(this.isEventTypeSelect));
        },
        getByParam(isType){
          return isType ? "type" : "topic";
        },
        updateAllColumns: function (byParam) {
            this.$http
                .get(this.servletUrl, {
                    params: { by: byParam },
                    headers: {
                        "Content-Type": "application/json",
                        Accept: "application/json",
                    },
                })
                .then((response) =>
                    response.json().then((data) => {
                        let listResultQuery = [];
                        if (data.length == 0) {
                            return;
                        }
                        data.forEach((element) => {
                            listResultQuery.push(element);
                        });
                        this.eventList = listResultQuery;
                        this.emptyMessage = "";
                    })
                );
        },
    },
});
