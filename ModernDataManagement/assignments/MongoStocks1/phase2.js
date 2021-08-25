/**
 * Finding min price of each stock - Phase 2
 * @author Tom Bohbot
 * @version March 16, 2021
 */

// Necessary info. to connect to mongo server:
const { MongoClient } = require("mongodb");
const uri = "mongodb+srv://tbohbot:leff@cluster.sq6jf.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"
const client = new MongoClient(uri);

/**
 * Phase 2: Query the downloaded data
 * @author Tom Bohbot
 */
 async function run() {
    try {
        console.log("PHASE 2");
        await client.connect();
        const database = client.db('mdm');
        const stocks = database.collection('stocks');
        // Do the query:
        console.log("Query the inputted data to find lowest stock price and data per company")
        query = await stocks.aggregate([{$sort: {Low: 1}}, {$group:{_id: "$Ticker Symbol", low: {$first: "$Low"}, date: {$first: "$Date"} }}, {$sort: {_id: 1}}]).toArray();
        console.log(query);
    } finally { 
        await client.close(); 
    }
}
run().catch(console.dir);