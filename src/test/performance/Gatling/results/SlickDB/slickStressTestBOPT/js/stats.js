var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "2000",
        "ok": "1998",
        "ko": "2"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "2",
        "ko": "3549"
    },
    "maxResponseTime": {
        "total": "8518",
        "ok": "8518",
        "ko": "4096"
    },
    "meanResponseTime": {
        "total": "1668",
        "ok": "1666",
        "ko": "3823"
    },
    "standardDeviation": {
        "total": "1865",
        "ok": "1864",
        "ko": "274"
    },
    "percentiles1": {
        "total": "1461",
        "ok": "1453",
        "ko": "3823"
    },
    "percentiles2": {
        "total": "2836",
        "ok": "2829",
        "ko": "3959"
    },
    "percentiles3": {
        "total": "4547",
        "ok": "4548",
        "ko": "4069"
    },
    "percentiles4": {
        "total": "8418",
        "ok": "8418",
        "ko": "4091"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 901,
    "percentage": 45
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 63,
    "percentage": 3
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1034,
    "percentage": 52
},
    "group4": {
    "name": "failed",
    "count": 2,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "142.857",
        "ok": "142.714",
        "ko": "0.143"
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
        "ok": "998",
        "ko": "2"
    },
    "minResponseTime": {
        "total": "833",
        "ok": "833",
        "ko": "3549"
    },
    "maxResponseTime": {
        "total": "8518",
        "ok": "8518",
        "ko": "4096"
    },
    "meanResponseTime": {
        "total": "3128",
        "ok": "3126",
        "ko": "3823"
    },
    "standardDeviation": {
        "total": "1529",
        "ok": "1531",
        "ko": "274"
    },
    "percentiles1": {
        "total": "2838",
        "ok": "2832",
        "ko": "3823"
    },
    "percentiles2": {
        "total": "3981",
        "ok": "3980",
        "ko": "3959"
    },
    "percentiles3": {
        "total": "4660",
        "ok": "4660",
        "ko": "4069"
    },
    "percentiles4": {
        "total": "8450",
        "ok": "8450",
        "ko": "4091"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 63,
    "percentage": 6
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 935,
    "percentage": 94
},
    "group4": {
    "name": "failed",
    "count": 2,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "71.429",
        "ok": "71.286",
        "ko": "0.143"
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
        "total": "2",
        "ok": "2",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2017",
        "ok": "2017",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "209",
        "ok": "209",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "596",
        "ok": "596",
        "ko": "-"
    },
    "percentiles1": {
        "total": "10",
        "ok": "10",
        "ko": "-"
    },
    "percentiles2": {
        "total": "16",
        "ok": "16",
        "ko": "-"
    },
    "percentiles3": {
        "total": "2005",
        "ok": "2005",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2009",
        "ok": "2009",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 901,
    "percentage": 90
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 99,
    "percentage": 10
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "71.429",
        "ok": "71.429",
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
