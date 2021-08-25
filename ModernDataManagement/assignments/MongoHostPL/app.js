/**
 * Patriarchy via Node.js
 * @author Tom Bohbot
 * @version March 3, 2021
 */

const { MongoClient } = require("mongodb");
// Replace the uri string with your MongoDB deployment's connection string.
const uri = "mongodb+srv://tbohbot:leff@cluster.sq6jf.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"
const client = new MongoClient(uri);
async function run() {
    try {
        // declare variables to reduce data entry errors:
        var firstNameVar;
        var lastNameVar;
        var dobVar;
        var id;
        var dad;
        var mom;
        var domVar;
        console.log("Connect to mongo server:");
        await client.connect();
        console.log("Use families database:");
        const database = client.db('people');
        console.log("Clear any pre-existing data:");
        database.dropCollection('families');
        const families = database.collection('families');

        console.log("Insert grandpa 1:");
        firstNameVar = "Maurice";
        lastNameVar = "Bohbot";
        dobVar = new Date('January 1, 1976 00:00:00');
        domVar = new Date('January 1, 1995 00:00:00');
        if (domVar.getFullYear() - dobVar.getFullYear() < 16) {
            throw 'Must be at least 16 to be married.';
        }
        id = firstNameVar + ":" + lastNameVar + ":" + dobVar;
        const granpaOne = {_id: id, firstName: firstNameVar,  lastName: lastNameVar, dob: dobVar, children: [], dom: domVar};
        const insertGranpaOne = await families.insertOne(granpaOne);
        console.trace(granpaOne);

        console.log("Insert grandma 1:");
        firstNameVar = "Denise";
        lastNameVar = "Bohbot";
        dobVar = new Date('January 1, 1977 00:00:00')
        if (domVar.getFullYear() - dobVar.getFullYear() < 16) {
            throw 'Must be at least 16 to be married.';
        }
        id = firstNameVar + ":" + lastNameVar + ":" + dobVar;
        const grandmaOne = {_id: id, firstName: firstNameVar, lastName: lastNameVar, dob: dobVar, children: [], dom: domVar};
        const insertGrandmaOne = await families.insertOne(grandmaOne);
        console.trace(grandmaOne);

        console.log("Insert grandpa 2:");
        firstNameVar = "David";
        lastNameVar = "Zerbib";
        dobVar = new Date('January 1, 1975 00:00:00')
        domVar = new Date('January 1, 1994')
        if (domVar.getFullYear() - dobVar.getFullYear() < 16) {
            throw 'Must be at least 16 to be married.';
        }
        id = firstNameVar + ":" + lastNameVar + ":" + dobVar;
        const grandpaTwo = {_id: id, firstName: firstNameVar, lastName: lastNameVar, dob: dobVar, children: [], dom: domVar};
        const insertGrandpaTwo = await families.insertOne(grandpaTwo);
        console.trace(grandpaTwo);

        console.log("Insert grandma 2:");
        firstNameVar = "Viveane";
        lastNameVar = "Zerbib";
        dobVar = new Date('January 1, 1974 00:00:00');
        if (domVar.getFullYear() - dobVar.getFullYear() < 16) {
            throw 'Must be at least 16 to be married.';
        }
        id = firstNameVar + ":" + lastNameVar + ":" + dobVar;
        const grandmaTwo = {_id: id, firstName: firstNameVar, lastName: lastNameVar, dob: dobVar, children: [], dom: domVar};
        const insertGrandmaTwo = await families.insertOne(grandmaTwo);
        console.trace(grandmaTwo);

        console.log("Insert father:");
        firstNameVar = "Daniel";
        lastNameVar = "Bohbot";
        dobVar = new Date('January 1, 2000 00:00:00');
        domVar = new Date ('January 1, 2019 00:00:00');
        if (domVar.getFullYear() - dobVar.getFullYear() < 16) {
            throw 'Must be at least 16 to be married.';
        }
        id = firstNameVar + ":" + lastNameVar + ":" + dobVar;
        dadArray = await families.find({firstName: "Maurice"}).toArray();
        dad = dadArray[0]
        dadAge = dobVar.getFullYear() - dad.dob.getFullYear()
        momArray = await families.find({firstName: "Denise"}).toArray();
        mom = momArray[0]
        momAge = dobVar.getFullYear() - mom.dob.getFullYear()
        if (domVar.getFullYear() - dobVar.getFullYear() < 16) {
            throw 'Must be at least 16 to be married.';
        }
        if (momAge < 17 || dadAge < 17) {
            throw "Parent must be at least 17 years older than child.";
        }
        const father = {_id: id, firstName: firstNameVar, lastName: lastNameVar, dob: dobVar, children: [], parent: {father: dad.firstName + " " + dad.lastName , mother: mom.firstName + " " + mom.lastName}, dom: domVar };
        const insertFather = await families.insertOne(father);
        console.trace(father);
        var dadsKids = dad.children;
        dadsKids.push(id);
        var momsKids = mom.children;
        momsKids.push(id);
        const updateGrandpaOne = await families.updateOne({_id: dad._id}, {$set: {children: dadsKids}});
        const updateGrandmaOne = await families.updateOne({_id: mom._id}, {$set: {children: momsKids}});
        console.log("update first grandpa's children");
        updateDad = await families.find({firstName: dad.firstName}).toArray()
        updateMom = await families.find({firstName: mom.firstName}).toArray()
        console.trace(updateDad[0]);
        console.log("update first grandma's children");
        console.trace(updateMom[0]);


        console.log("Insert mother:");
        firstNameVar = "Stephanie";
        lastNameVar = "Bohbot";
        dobVar = new Date('January 1, 2001 00:00:00');
        if (domVar.getFullYear() - dobVar.getFullYear() < 16) {
            throw 'Must be at least 16 to be married.';
        }
        id = firstNameVar + ":" + lastNameVar + ":" + dobVar;
        dadArray = await families.find({firstName: "David"}).toArray();
        dad = dadArray[0]
        dadAge = dobVar.getFullYear() - dad.dob.getFullYear()
        momArray = await families.find({firstName: "Viveane"}).toArray();
        mom = momArray[0]
        momAge = dobVar.getFullYear() - mom.dob.getFullYear()
        if (momAge < 17 || dadAge < 17) {
            throw "Parent must be at least 17 years older than child.";
        }
        const mother = {_id: id, firstName: firstNameVar, lastName: lastNameVar, dob: dobVar, children: [], parent: {father: dad.firstName + " " + dad.lastName , mother: mom.firstName + " " + mom.lastName}, dom: domVar };
        const insertMother = await families.insertOne(mother);
        console.trace(mother);
        var dadsKids = dad.children;
        dadsKids.push(id);
        var momsKids = mom.children;
        momsKids.push(id);
        const updateGrandpaTwo = await families.updateOne({_id: dad._id}, {$set: {children: dadsKids}});
        const updateGrandmaTwo = await families.updateOne({_id: mom._id}, {$set: {children: momsKids}});  
        updateDad = await families.find({firstName: dad.firstName}).toArray()
        updateMom = await families.find({firstName: mom.firstName}).toArray()      
        console.log("update second grandpa's children");
        console.trace(updateDad[0]);
        console.log("update second grandma's children");
        console.trace(updateMom[0]);

        console.log("Insert child 1:");
        firstNameVar = "Tom";
        lastNameVar = "Bohbot";
        dobVar = new Date('January 1, 2021 00:00:00')
        id = firstNameVar + ":" + lastNameVar + ":" + dobVar;
        dadArray = await families.find({firstName: "Daniel"}).toArray();
        dad = dadArray[0]
        dadAge = dobVar.getFullYear() - dad.dob.getFullYear()
        momArray = await families.find({firstName: "Stephanie"}).toArray();
        mom = momArray[0]
        momAge = dobVar.getFullYear() - mom.dob.getFullYear()
        if (momAge < 17 || dadAge < 17) {
            throw "Parent must be at least 17 years older than child.";
        }
        const childOne = {_id: id, firstName: firstNameVar, lastName: lastNameVar, dob: dobVar, children: [], parent: {father: dad.firstName + " " + dad.lastName , mother: mom.firstName + " " + mom.lastName} };
        const insertChildOne = await families.insertOne(childOne);
        console.trace(childOne);
        var dadsKids = dad.children;
        dadsKids.push(id);
        var momsKids = mom.children;
        momsKids.push(id);
        const updateFatherOne = await families.updateOne({_id: dad._id}, {$set: {children: dadsKids}});
        const updateMotherOne = await families.updateOne({_id: mom._id}, {$set: {children: momsKids}});  
        updateDad = await families.find({firstName: dad.firstName}).toArray()
        updateMom = await families.find({firstName: mom.firstName}).toArray()      
        console.log("update father's children");
        console.trace(updateDad[0]);
        console.log("update mother's children");
        console.trace(updateMom[0]);    

        console.log("Insert child 2:");
        firstNameVar = "Tania";
        lastNameVar = "Bohbot";
        dobVar = new Date('January 1, 2021 00:00:00')
        id = firstNameVar + ":" + lastNameVar + ":" + dobVar;
        dadArray = await families.find({firstName: "Daniel"}).toArray();
        dad = dadArray[0]
        dadAge = dobVar.getFullYear() - dad.dob.getFullYear()
        momArray = await families.find({firstName: "Stephanie"}).toArray();
        mom = momArray[0]
        momAge = dobVar.getFullYear() - mom.dob.getFullYear()
        if (momAge < 17 || dadAge < 17) {
            throw "Parent must be at least 17 years older than child.";
        }
        const childTwo = {_id: id, firstName: firstNameVar, lastName: lastNameVar, dob: dobVar, children: [], parent: {father: dad.firstName + " " + dad.lastName , mother: mom.firstName + " " + mom.lastName} };
        const insertchildTwo = await families.insertOne(childTwo);
        console.trace(childTwo);
        var dadsKids = dad.children;
        dadsKids.push(id);
        var momsKids = mom.children;
        momsKids.push(id);
        const updateFatherTwo = await families.updateOne({_id: dad._id}, {$set: {children: dadsKids}});
        const updateMotherTwo = await families.updateOne({_id: mom._id}, {$set: {children: momsKids}}); 
        updateDad = await families.find({firstName: dad.firstName}).toArray()
        updateMom = await families.find({firstName: mom.firstName}).toArray()      
        console.log("update father's children");
        console.trace(updateDad[0]);
        console.log("update mother's children");
        console.trace(updateMom[0]);    

        console.log("Query the family by sorting dob and then lastName")
        queryInArray = await families.find().sort({dob: 1, lastName: 1}).toArray();
        query = queryInArray
        console.log(query);
    } finally { 
        await client.close(); 
    }
}
run().catch(console.dir);