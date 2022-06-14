var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "2000",
        "ok": "887",
        "ko": "1113"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "846",
        "ko": "2"
    },
    "maxResponseTime": {
        "total": "60017",
        "ok": "9357",
        "ko": "60017"
    },
    "meanResponseTime": {
        "total": "6242",
        "ok": "4613",
        "ko": "7541"
    },
    "standardDeviation": {
        "total": "14548",
        "ok": "2521",
        "ko": "19273"
    },
    "percentiles1": {
        "total": "1918",
        "ok": "3883",
        "ko": "23"
    },
    "percentiles2": {
        "total": "4881",
        "ok": "6510",
        "ko": "1139"
    },
    "percentiles3": {
        "total": "60010",
        "ok": "8958",
        "ko": "60012"
    },
    "percentiles4": {
        "total": "60014",
        "ok": "9269",
        "ko": "60014"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 49,
    "percentage": 2
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 838,
    "percentage": 42
},
    "group4": {
    "name": "failed",
    "count": 1113,
    "percentage": 56
},
    "meanNumberOfRequestsPerSecond": {
        "total": "27.397",
        "ok": "12.151",
        "ko": "15.247"
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
        "ok": "887",
        "ko": "113"
    },
    "minResponseTime": {
        "total": "846",
        "ok": "846",
        "ko": "2701"
    },
    "maxResponseTime": {
        "total": "9357",
        "ok": "9357",
        "ko": "4702"
    },
    "meanResponseTime": {
        "total": "4458",
        "ok": "4613",
        "ko": "3247"
    },
    "standardDeviation": {
        "total": "2426",
        "ok": "2521",
        "ko": "729"
    },
    "percentiles1": {
        "total": "3740",
        "ok": "3883",
        "ko": "2820"
    },
    "percentiles2": {
        "total": "6210",
        "ok": "6510",
        "ko": "4031"
    },
    "percentiles3": {
        "total": "8881",
        "ok": "8958",
        "ko": "4601"
    },
    "percentiles4": {
        "total": "9256",
        "ok": "9269",
        "ko": "4691"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 49,
    "percentage": 5
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 838,
    "percentage": 84
},
    "group4": {
    "name": "failed",
    "count": 113,
    "percentage": 11
},
    "meanNumberOfRequestsPerSecond": {
        "total": "13.699",
        "ok": "12.151",
        "ko": "1.548"
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
        "ok": "0",
        "ko": "1000"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "-",
        "ko": "2"
    },
    "maxResponseTime": {
        "total": "60017",
        "ok": "-",
        "ko": "60017"
    },
    "meanResponseTime": {
        "total": "8026",
        "ok": "-",
        "ko": "8026"
    },
    "standardDeviation": {
        "total": "20274",
        "ok": "-",
        "ko": "20274"
    },
    "percentiles1": {
        "total": "20",
        "ok": "-",
        "ko": "20"
    },
    "percentiles2": {
        "total": "53",
        "ok": "-",
        "ko": "53"
    },
    "percentiles3": {
        "total": "60012",
        "ok": "-",
        "ko": "60012"
    },
    "percentiles4": {
        "total": "60014",
        "ok": "-",
        "ko": "60014"
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
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 1000,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "13.699",
        "ok": "-",
        "ko": "13.699"
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
