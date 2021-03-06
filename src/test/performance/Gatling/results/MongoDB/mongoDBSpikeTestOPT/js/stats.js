var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "40000",
        "ok": "8505",
        "ko": "31495"
    },
    "minResponseTime": {
        "total": "79",
        "ok": "79",
        "ko": "8670"
    },
    "maxResponseTime": {
        "total": "94429",
        "ok": "44789",
        "ko": "94429"
    },
    "meanResponseTime": {
        "total": "38578",
        "ok": "15133",
        "ko": "44909"
    },
    "standardDeviation": {
        "total": "18301",
        "ok": "10151",
        "ko": "14457"
    },
    "percentiles1": {
        "total": "38373",
        "ok": "14504",
        "ko": "43502"
    },
    "percentiles2": {
        "total": "52200",
        "ok": "22584",
        "ko": "55451"
    },
    "percentiles3": {
        "total": "66472",
        "ok": "32636",
        "ko": "67827"
    },
    "percentiles4": {
        "total": "84442",
        "ok": "38384",
        "ko": "84595"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 705,
    "percentage": 2
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 190,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 7610,
    "percentage": 19
},
    "group4": {
    "name": "failed",
    "count": 31495,
    "percentage": 79
},
    "meanNumberOfRequestsPerSecond": {
        "total": "360.36",
        "ok": "76.622",
        "ko": "283.739"
    }
},
contents: {
"req_request-0-684d2": {
        type: "REQUEST",
        name: "request_0",
path: "request_0",
pathFormatted: "req_request-0-684d2",
stats: {
    "name": "request_0",
    "numberOfRequests": {
        "total": "20000",
        "ok": "1870",
        "ko": "18130"
    },
    "minResponseTime": {
        "total": "6612",
        "ok": "6612",
        "ko": "17571"
    },
    "maxResponseTime": {
        "total": "94429",
        "ok": "30836",
        "ko": "94429"
    },
    "meanResponseTime": {
        "total": "42169",
        "ok": "16193",
        "ko": "44848"
    },
    "standardDeviation": {
        "total": "17131",
        "ok": "5576",
        "ko": "15613"
    },
    "percentiles1": {
        "total": "40313",
        "ok": "15575",
        "ko": "42348"
    },
    "percentiles2": {
        "total": "55109",
        "ok": "20665",
        "ko": "55960"
    },
    "percentiles3": {
        "total": "70731",
        "ok": "25503",
        "ko": "73346"
    },
    "percentiles4": {
        "total": "86221",
        "ok": "30823",
        "ko": "86292"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1870,
    "percentage": 9
},
    "group4": {
    "name": "failed",
    "count": 18130,
    "percentage": 91
},
    "meanNumberOfRequestsPerSecond": {
        "total": "180.18",
        "ok": "16.847",
        "ko": "163.333"
    }
}
    },"req_request-1-46da4": {
        type: "REQUEST",
        name: "request_1",
path: "request_1",
pathFormatted: "req_request-1-46da4",
stats: {
    "name": "request_1",
    "numberOfRequests": {
        "total": "20000",
        "ok": "6635",
        "ko": "13365"
    },
    "minResponseTime": {
        "total": "79",
        "ok": "79",
        "ko": "8670"
    },
    "maxResponseTime": {
        "total": "77587",
        "ok": "44789",
        "ko": "77587"
    },
    "meanResponseTime": {
        "total": "34987",
        "ok": "14834",
        "ko": "44993"
    },
    "standardDeviation": {
        "total": "18723",
        "ok": "11087",
        "ko": "12722"
    },
    "percentiles1": {
        "total": "36277",
        "ok": "13983",
        "ko": "44313"
    },
    "percentiles2": {
        "total": "49125",
        "ok": "23485",
        "ko": "54508"
    },
    "percentiles3": {
        "total": "64283",
        "ok": "33621",
        "ko": "65104"
    },
    "percentiles4": {
        "total": "67502",
        "ok": "39327",
        "ko": "67881"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 705,
    "percentage": 4
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 190,
    "percentage": 1
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 5740,
    "percentage": 29
},
    "group4": {
    "name": "failed",
    "count": 13365,
    "percentage": 67
},
    "meanNumberOfRequestsPerSecond": {
        "total": "180.18",
        "ok": "59.775",
        "ko": "120.405"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
