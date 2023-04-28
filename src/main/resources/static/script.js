function checkAnswer(questionNumber, correctAnswer) {
    var radios = document.getElementsByName("quiz" + questionNumber);
    var labels = document.getElementsByName("label_quiz" + questionNumber);
    
    for (var i = 0; i < radios.length; i++) 
    {
        if (radios[i].value == correctAnswer) 
        {
            labels[i].classList.add("correct");
        } 
        else {
            labels[i].classList.add("incorrect");
        }
        
        radios[i].disabled = true;
    }
}

function openModal() {
    document.getElementById("quizModal").style.display = "block";
}

function openMaze() {
    window.open("/maze/maze.html", "_blank");
}

function aboutMaze() {
    window.open("/maze/about.html", "_blank");
}

function closeModal() {
    document.getElementById("quizModal").style.display = "none";
}