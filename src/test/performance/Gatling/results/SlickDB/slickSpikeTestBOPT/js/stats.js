var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "40000",
        "ok": "9704",
        "ko": "30296"
    },
    "minResponseTime": {
        "total": "94",
        "ok": "94",
        "ko": "8234"
    },
    "maxResponseTime": {
        "total": "93216",
        "ok": "45580",
        "ko": "93216"
    },
    "meanResponseTime": {
        "total": "40072",
        "ok": "16367",
        "ko": "47665"
    },
    "standardDeviation": {
        "total": "19936",
        "ok": "10940",
        "ko": "15773"
    },
    "percentiles1": {
        "total": "39634",
        "ok": "16678",
        "ko": "45534"
    },
    "percentiles2": {
        "total": "54659",
        "ok": "25119",
        "ko": "59299"
    },
    "percentiles3": {
        "total": "72520",
        "ok": "33846",
        "ko": "74307"
    },
    "percentiles4": {
        "total": "84946",
        "ok": "40103",
        "ko": "85606"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 787,
    "percentage": 2
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 368,
    "percentage": 1
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 8549,
    "percentage": 21
},
    "group4": {
    "name": "failed",
    "count": 30296,
    "percentage": 76
},
    "meanNumberOfRequestsPerSecond": {
        "total": "333.333",
        "ok": "80.867",
        "ko": "252.467"
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
        "ok": "2611",
        "ko": "17389"
    },
    "minResponseTime": {
        "total": "3667",
        "ok": "3667",
        "ko": "15658"
    },
    "maxResponseTime": {
        "total": "93216",
        "ok": "38408",
        "ko": "93216"
    },
    "meanResponseTime": {
        "total": "42338",
        "ok": "17552",
        "ko": "46059"
    },
    "standardDeviation": {
        "total": "18740",
        "ok": "6537",
        "ko": "17070"
    },
    "percentiles1": {
        "total": "39770",
        "ok": "16802",
        "ko": "42321"
    },
    "percentiles2": {
        "total": "54682",
        "ok": "22342",
        "ko": "58121"
    },
    "percentiles3": {
        "total": "77515",
        "ok": "29213",
        "ko": "80264"
    },
    "percentiles4": {
        "total": "86261",
        "ok": "36791",
        "ko": "86359"
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
    "count": 2611,
    "percentage": 13
},
    "group4": {
    "name": "failed",
    "count": 17389,
    "percentage": 87
},
    "meanNumberOfRequestsPerSecond": {
        "total": "166.667",
        "ok": "21.758",
        "ko": "144.908"
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
        "ok": "7093",
        "ko": "12907"
    },
    "minResponseTime": {
        "total": "94",
        "ok": "94",
        "ko": "8234"
    },
    "maxResponseTime": {
        "total": "77512",
        "ok": "45580",
        "ko": "77512"
    },
    "meanResponseTime": {
        "total": "37807",
        "ok": "15931",
        "ko": "49829"
    },
    "standardDeviation": {
        "total": "20820",
        "ok": "12137",
        "ko": "13535"
    },
    "percentiles1": {
        "total": "39604",
        "ok": "16627",
        "ko": "49886"
    },
    "percentiles2": {
        "total": "54591",
        "ok": "26761",
        "ko": "60183"
    },
    "percentiles3": {
        "total": "70165",
        "ok": "34820",
        "ko": "71282"
    },
    "percentiles4": {
        "total": "73984",
        "ok": "40610",
        "ko": "74289"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 787,
    "percentage": 4
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 368,
    "percentage": 2
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 5938,
    "percentage": 30
},
    "group4": {
    "name": "failed",
    "count": 12907,
    "percentage": 65
},
    "meanNumberOfRequestsPerSecond": {
        "total": "166.667",
        "ok": "59.108",
        "ko": "107.558"
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
