function fitness(word){
  let matches = 0
  for(let i = 0; i<goal.length; i++){
    if(word[i] == goal[i]){
      matches++
    }
  }
  return matches
}

function smartFitness(word){
  let matches = 0
  for(let i = 0; i<goal.length; i++){
    if(word[i] == goal[i]){
      matches++
      memo[i].solved = true
    }
  }
  return matches
}

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min +1)) + min;
}

function makeRandomMutation(word){
  let index = getRandomInt(0,word.length-1)
  let c = String.fromCharCode(getRandomInt(0,127));

  let newWord = ""
  for(let i = 0; i<word.length; i++){
    if(i != index){
      newWord += word[i]
    }else{
      newWord += c
    }
  }
  return newWord
}

//using memoization for efficiency improvement
let memo = {};
function makeSmartMutation(word){
  let index = 0
  let c = ''
  while(true){
    index = getRandomInt(0,word.length-1)
    c = String.fromCharCode(getRandomInt(0,127));
    if(memo[index]){
      if(memo[index].solved == true){
        //letter at index is already correct => dont make mutation and generate new random index
        continue;
      }
      if(memo[index].letters.includes(c)){
        //generated char has already been checked at this index => dont make mutation and generate new random index
        continue;
      }
      //index has been mutated but not with current character => make the mutation
      memo[index].letters.push(c);
      break;
    }
    //index has not been mutated yet => make the mutation
    memo[index] = {solved:false, letters:[c]};
    break;
  }

  let newWord = ""
  for(let i = 0; i<word.length; i++){
    if(i != index){
      newWord += word[i]
    }else{
      newWord += c
    }
  }
  return newWord
}

// let goal = "Hello"
let goal = process.argv.slice(2, process.argv.length).join(' ')

let candidate = "_".repeat(goal.length) //breed()

let time = Date.now()
while(candidate != goal){
  //random version
  let mutatedCandidate = makeRandomMutation(candidate);
  if(fitness(mutatedCandidate) > fitness(candidate)){
    candidate = mutatedCandidate;
    console.log(mutatedCandidate)
  }
  
  //smart version
  // let mutatedCandidate = makeSmartMutation(candidate);
  // if(smartFitness(mutatedCandidate) > fitness(candidate)){
  //   candidate = mutatedCandidate;
  //   console.log(mutatedCandidate)
  // }

}
// console.log(Date.now() - time);
