var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "1000",
        "ok": "1000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "5",
        "ok": "5",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "818",
        "ok": "818",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "62",
        "ok": "62",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "80",
        "ok": "80",
        "ko": "-"
    },
    "percentiles1": {
        "total": "31",
        "ok": "31",
        "ko": "-"
    },
    "percentiles2": {
        "total": "111",
        "ok": "111",
        "ko": "-"
    },
    "percentiles3": {
        "total": "173",
        "ok": "173",
        "ko": "-"
    },
    "percentiles4": {
        "total": "190",
        "ok": "190",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 995,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 5,
    "percentage": 1
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "4.651",
        "ok": "4.651",
        "ko": "-"
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
        "total": "500",
        "ok": "500",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "5",
        "ok": "5",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "818",
        "ok": "818",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "18",
        "ok": "18",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "81",
        "ok": "81",
        "ko": "-"
    },
    "percentiles1": {
        "total": "7",
        "ok": "7",
        "ko": "-"
    },
    "percentiles2": {
        "total": "9",
        "ok": "9",
        "ko": "-"
    },
    "percentiles3": {
        "total": "24",
        "ok": "24",
        "ko": "-"
    },
    "percentiles4": {
        "total": "116",
        "ok": "116",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 495,
    "percentage": 99
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 5,
    "percentage": 1
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.326",
        "ok": "2.326",
        "ko": "-"
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
        "total": "500",
        "ok": "500",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "17",
        "ok": "17",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "199",
        "ok": "199",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "106",
        "ok": "106",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "48",
        "ok": "48",
        "ko": "-"
    },
    "percentiles1": {
        "total": "110",
        "ok": "110",
        "ko": "-"
    },
    "percentiles2": {
        "total": "142",
        "ok": "142",
        "ko": "-"
    },
    "percentiles3": {
        "total": "181",
        "ok": "181",
        "ko": "-"
    },
    "percentiles4": {
        "total": "190",
        "ok": "190",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 500,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.326",
        "ok": "2.326",
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
