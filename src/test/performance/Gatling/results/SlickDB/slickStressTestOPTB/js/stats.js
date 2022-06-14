var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "40000",
        "ok": "1872",
        "ko": "38128"
    },
    "minResponseTime": {
        "total": "108",
        "ok": "5405",
        "ko": "108"
    },
    "maxResponseTime": {
        "total": "101323",
        "ok": "75521",
        "ko": "101323"
    },
    "meanResponseTime": {
        "total": "44683",
        "ok": "17956",
        "ko": "45996"
    },
    "standardDeviation": {
        "total": "19395",
        "ok": "8423",
        "ko": "18824"
    },
    "percentiles1": {
        "total": "43863",
        "ok": "16372",
        "ko": "44871"
    },
    "percentiles2": {
        "total": "60008",
        "ok": "21984",
        "ko": "60011"
    },
    "percentiles3": {
        "total": "73488",
        "ok": "31851",
        "ko": "73572"
    },
    "percentiles4": {
        "total": "93136",
        "ok": "37495",
        "ko": "93164"
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
    "count": 1872,
    "percentage": 5
},
    "group4": {
    "name": "failed",
    "count": 38128,
    "percentage": 95
},
    "meanNumberOfRequestsPerSecond": {
        "total": "245.399",
        "ok": "11.485",
        "ko": "233.914"
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
        "ok": "1859",
        "ko": "18141"
    },
    "minResponseTime": {
        "total": "5405",
        "ok": "5405",
        "ko": "20736"
    },
    "maxResponseTime": {
        "total": "101323",
        "ok": "37911",
        "ko": "101323"
    },
    "meanResponseTime": {
        "total": "44076",
        "ok": "17554",
        "ko": "46794"
    },
    "standardDeviation": {
        "total": "19124",
        "ok": "6936",
        "ko": "17856"
    },
    "percentiles1": {
        "total": "40404",
        "ok": "16364",
        "ko": "41649"
    },
    "percentiles2": {
        "total": "57452",
        "ok": "21530",
        "ko": "59998"
    },
    "percentiles3": {
        "total": "76302",
        "ok": "31829",
        "ko": "76708"
    },
    "percentiles4": {
        "total": "94194",
        "ok": "37302",
        "ko": "100646"
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
    "count": 1859,
    "percentage": 9
},
    "group4": {
    "name": "failed",
    "count": 18141,
    "percentage": 91
},
    "meanNumberOfRequestsPerSecond": {
        "total": "122.699",
        "ok": "11.405",
        "ko": "111.294"
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
        "ok": "13",
        "ko": "19987"
    },
    "minResponseTime": {
        "total": "108",
        "ok": "75517",
        "ko": "108"
    },
    "maxResponseTime": {
        "total": "90061",
        "ok": "75521",
        "ko": "90061"
    },
    "meanResponseTime": {
        "total": "45291",
        "ok": "75519",
        "ko": "45271"
    },
    "standardDeviation": {
        "total": "19643",
        "ok": "1",
        "ko": "19634"
    },
    "percentiles1": {
        "total": "48459",
        "ok": "75519",
        "ko": "48442"
    },
    "percentiles2": {
        "total": "60010",
        "ok": "75520",
        "ko": "60010"
    },
    "percentiles3": {
        "total": "71090",
        "ok": "75520",
        "ko": "71089"
    },
    "percentiles4": {
        "total": "75686",
        "ok": "75521",
        "ko": "75688"
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
    "count": 13,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 19987,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "122.699",
        "ok": "0.08",
        "ko": "122.62"
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
