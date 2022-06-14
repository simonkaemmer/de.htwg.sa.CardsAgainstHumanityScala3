var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "60000",
        "ok": "4263",
        "ko": "55737"
    },
    "minResponseTime": {
        "total": "396",
        "ok": "396",
        "ko": "37851"
    },
    "maxResponseTime": {
        "total": "152216",
        "ok": "134940",
        "ko": "152216"
    },
    "meanResponseTime": {
        "total": "88136",
        "ok": "33813",
        "ko": "92291"
    },
    "standardDeviation": {
        "total": "27977",
        "ok": "29188",
        "ko": "23118"
    },
    "percentiles1": {
        "total": "95234",
        "ok": "24998",
        "ko": "96139"
    },
    "percentiles2": {
        "total": "107020",
        "ok": "58412",
        "ko": "107618"
    },
    "percentiles3": {
        "total": "125677",
        "ok": "81780",
        "ko": "126584"
    },
    "percentiles4": {
        "total": "141169",
        "ok": "99980",
        "ko": "141178"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 16,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 42,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 4205,
    "percentage": 7
},
    "group4": {
    "name": "failed",
    "count": 55737,
    "percentage": 93
},
    "meanNumberOfRequestsPerSecond": {
        "total": "283.019",
        "ok": "20.108",
        "ko": "262.91"
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
        "total": "30000",
        "ok": "1529",
        "ko": "28471"
    },
    "minResponseTime": {
        "total": "3005",
        "ok": "3005",
        "ko": "37851"
    },
    "maxResponseTime": {
        "total": "114942",
        "ok": "107520",
        "ko": "114942"
    },
    "meanResponseTime": {
        "total": "77097",
        "ok": "37911",
        "ko": "79201"
    },
    "standardDeviation": {
        "total": "22997",
        "ok": "23265",
        "ko": "21007"
    },
    "percentiles1": {
        "total": "86979",
        "ok": "31881",
        "ko": "90075"
    },
    "percentiles2": {
        "total": "95792",
        "ok": "57730",
        "ko": "96105"
    },
    "percentiles3": {
        "total": "103396",
        "ok": "77782",
        "ko": "103859"
    },
    "percentiles4": {
        "total": "111515",
        "ok": "86450",
        "ko": "111571"
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
    "count": 1529,
    "percentage": 5
},
    "group4": {
    "name": "failed",
    "count": 28471,
    "percentage": 95
},
    "meanNumberOfRequestsPerSecond": {
        "total": "141.509",
        "ok": "7.212",
        "ko": "134.297"
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
        "total": "30000",
        "ok": "2734",
        "ko": "27266"
    },
    "minResponseTime": {
        "total": "396",
        "ok": "396",
        "ko": "52666"
    },
    "maxResponseTime": {
        "total": "152216",
        "ok": "134940",
        "ko": "152216"
    },
    "meanResponseTime": {
        "total": "99175",
        "ok": "31520",
        "ko": "105959"
    },
    "standardDeviation": {
        "total": "28157",
        "ok": "31797",
        "ko": "16309"
    },
    "percentiles1": {
        "total": "105642",
        "ok": "11593",
        "ko": "107275"
    },
    "percentiles2": {
        "total": "114142",
        "ok": "59116",
        "ko": "114706"
    },
    "percentiles3": {
        "total": "133105",
        "ok": "86596",
        "ko": "134036"
    },
    "percentiles4": {
        "total": "144220",
        "ok": "115611",
        "ko": "144701"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 16,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 42,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 2676,
    "percentage": 9
},
    "group4": {
    "name": "failed",
    "count": 27266,
    "percentage": 91
},
    "meanNumberOfRequestsPerSecond": {
        "total": "141.509",
        "ok": "12.896",
        "ko": "128.613"
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
