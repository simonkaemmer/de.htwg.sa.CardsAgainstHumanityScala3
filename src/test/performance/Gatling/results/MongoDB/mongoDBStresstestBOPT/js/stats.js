var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "2000",
        "ok": "1942",
        "ko": "58"
    },
    "minResponseTime": {
        "total": "24",
        "ok": "24",
        "ko": "1316"
    },
    "maxResponseTime": {
        "total": "17209",
        "ok": "17209",
        "ko": "2497"
    },
    "meanResponseTime": {
        "total": "3351",
        "ok": "3401",
        "ko": "1665"
    },
    "standardDeviation": {
        "total": "4073",
        "ok": "4123",
        "ko": "367"
    },
    "percentiles1": {
        "total": "2007",
        "ok": "2068",
        "ko": "1535"
    },
    "percentiles2": {
        "total": "3200",
        "ok": "3230",
        "ko": "1582"
    },
    "percentiles3": {
        "total": "16330",
        "ok": "16456",
        "ko": "2380"
    },
    "percentiles4": {
        "total": "16810",
        "ok": "16810",
        "ko": "2467"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 451,
    "percentage": 23
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 225,
    "percentage": 11
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1266,
    "percentage": 63
},
    "group4": {
    "name": "failed",
    "count": 58,
    "percentage": 3
},
    "meanNumberOfRequestsPerSecond": {
        "total": "90.909",
        "ok": "88.273",
        "ko": "2.636"
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
        "total": "1000",
        "ok": "942",
        "ko": "58"
    },
    "minResponseTime": {
        "total": "405",
        "ok": "405",
        "ko": "1316"
    },
    "maxResponseTime": {
        "total": "17209",
        "ok": "17209",
        "ko": "2497"
    },
    "meanResponseTime": {
        "total": "4856",
        "ok": "5052",
        "ko": "1665"
    },
    "standardDeviation": {
        "total": "5032",
        "ok": "5120",
        "ko": "367"
    },
    "percentiles1": {
        "total": "2441",
        "ok": "2573",
        "ko": "1535"
    },
    "percentiles2": {
        "total": "9242",
        "ok": "9288",
        "ko": "1582"
    },
    "percentiles3": {
        "total": "16702",
        "ok": "16710",
        "ko": "2380"
    },
    "percentiles4": {
        "total": "16841",
        "ok": "16843",
        "ko": "2467"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 71,
    "percentage": 7
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 84,
    "percentage": 8
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 787,
    "percentage": 79
},
    "group4": {
    "name": "failed",
    "count": 58,
    "percentage": 6
},
    "meanNumberOfRequestsPerSecond": {
        "total": "45.455",
        "ok": "42.818",
        "ko": "2.636"
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
        "total": "1000",
        "ok": "1000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "24",
        "ok": "24",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "8377",
        "ok": "8377",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1846",
        "ok": "1846",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1826",
        "ok": "1826",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1113",
        "ok": "1113",
        "ko": "-"
    },
    "percentiles2": {
        "total": "2633",
        "ok": "2633",
        "ko": "-"
    },
    "percentiles3": {
        "total": "7339",
        "ok": "7339",
        "ko": "-"
    },
    "percentiles4": {
        "total": "7974",
        "ok": "7974",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 380,
    "percentage": 38
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 141,
    "percentage": 14
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 479,
    "percentage": 48
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "45.455",
        "ok": "45.455",
        "ko": "-"
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
