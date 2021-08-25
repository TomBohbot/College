/**
 * Finding min price of each stock - Phase 1
 * @author Tom Bohbot
 * @version March 16, 2021
 */

// Necessary info. to connect to mongo server:
const { MongoClient } = require("mongodb");
const uri = "mongodb+srv://tbohbot:leff@cluster.sq6jf.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"
const client = new MongoClient(uri);
// read stocks data, put outside of asynch to avoid seg faults:
const fs = require('fs');
let stock_data = fs.readFileSync('stock3_companiesarray_stockeventarray.json');  
let stock = JSON.parse(stock_data); 

/**
* Phase 1: Connect to mongo server and download data
# @author Tom Bohbot
*/
async function run() {
    try {
        console.log("Connect to mongo server:");
        await client.connect();
        // create database and collection:
        console.log("Use mdm database:");
        const database = client.db('mdm');
        console.log("Clear any pre-existing data:");
        database.dropCollection('stocks');
        const stocks = database.collection('stocks');
        // insert each stock read above into MongoDB:
        var i;
        for (i = 0; i < stock.length; i++) {
            await stocks.insertMany(stock[i]);
        }
    } finally { 
        await client.close(); 
    }
}
run().catch(console.dir);


/**
 * Phase 1 Test Script
 * @author Dr. Avraham Leff
 * ran this code in the shell, not from this js file, output can be found in report.
 */
//  use mdm;
//  db.stocks.find().limit(1).pretty();
//  const numberOfStocks = db.stocks.distinct('Ticker Symbol').length;
//  print('number of ticker symbols', numberOfStocks);
//  print('first ticker symbol', db.stocks.distinct('Ticker Symbol')[0]);
//  print('eleventh ticker symbol', db.stocks.distinct('Ticker Symbol')[10]);
//  print('last ticker symbol', db.stocks.distinct('Ticker Symbol')[numberOfStocks - 1]);