//import
const { Bodies, Body, Composite, Engine, Events, Render, Runner, Sleeping } =Matter;

const width = 350;
const height = 450;
const wall_t = 10;
const deadline = 100; // ゲームオーバーになる高さ
const friction = 0.3; // 摩擦
const mass = 1; // 重量
const max_level = 11;
const wall_color = "#E7AE4D";
const density=1;
const bubble_colors = {
  0: "#ff7f7f",
  1: "#ff7fbf",
  2: "#ff7fff",
  3: "#bf7fff",
  4: "#7f7fff",
  5: "#7fbfff",
  6: "#7fffff",
  7: "#7fffbf",
  8: "#7fff7f",
  9: "#bfff7f",
  10: "#ffff7f",
};
const bubble_bg={
  0: "images/sakura.png",
  1: "images/ichigo.png",
  2: "images/budou.png",
  3: "images/mikann.png",
  4: "images/kaki.png",
  5: "images/ringo.png",
  6: "images/nasi.png",
  7: "images/momo.png",
  8: "images/paina.png",
  9: "images/melon.png",
  10: "images/suika.png"
}

const nextbubble_bg={
  0: "images/sakuranext.png",
  1: "images/ichigonext.png",
  2: "images/budounext.png",
  3: "images/mikannnext.png",
  4: "images/kakinext.png",
  5: "images/ringonext.png",
  6: "images/nasinext.png",
  7: "images/momonext.png",
  8: "images/painanext.png",
  9: "images/melonnext.png",
  10: "images/suikanext.png"
}

const gamebox = document.querySelector(".gamebox");
const message = document.querySelector(".message");
const score = document.querySelector(".score");
const nextcontainer=document.querySelector(".nextcontainer");

const object_categories = {
  wall: 0x0001,
  bubble: 0x0002,
  bubble_pending: 0x0004,
};

class bubbleGame {
  currentbubble = undefined;
  gameover = false;
  defaultX = width / 2;
  valArray=[]
  level2= Math.floor(Math.random() * 5);

  constructor() {
    this.engine = Engine.create();
    this.render = Render.create({
      element: gamebox,
      engine: this.engine,
      options: {
        width: width,
        height: height,
        wireframes: false, //図形塗りつぶし
        background:"#eedcb3"
      },
    });
    this.runner = Runner.create();
    Render.run(this.render);
    gamebox.addEventListener("click", this.handleClick.bind(this));
    gamebox.addEventListener("mousemove", this.handleMouseMove.bind(this));    
    Events.on(this.engine, "collisionStart", this.handleCollision.bind(this));  //Events.onはengineのイベントを仕掛ける
    Events.on(this.engine, "afterUpdate", this.checkGameOver.bind(this));
  }

  init() {
    // リセット時も使うので一旦全部消す
    Composite.clear(this.engine.world);
    this.resetMessage();
    gamebox.style.display="none"
    score.style.display="none"

    this.gameover = false;
    this.setScore(0);

    // 地面と壁作成
    const ground = Bodies.rectangle(
      width / 2,
      height - wall_t / 2,
      width,
      wall_t,
      {
        isStatic: true,
        label: "ground",
        render: {
          fillStyle: wall_color,
        },
      }
    );
    const leftwall = Bodies.rectangle(wall_t / 2, height / 2, wall_t, height, {
      isStatic: true,
      label: "leftwall",
      render: {
        fillStyle: wall_color,
      },
    });
    const rightwall = Bodies.rectangle(
      width - wall_t / 2,
      height / 2,
      wall_t,
      height,
      {
        isStatic: true,
        label: "rightwall",
        render: {
          fillStyle: wall_color,
        },
      }
    );
    Composite.add(this.engine.world, [ground, leftwall, rightwall]);
    Runner.run(this.runner, this.engine);

    this.gameStatus = "ready";
    //this.showReadyMessage();
    this.start(this);
  }

	showReadyMessage() {
	  console.log("showReadyMessage呼ばれた");
	  const p = document.createElement("p");
	  p.classList.add("mainText");
      p.textContent = "バブルゲーム";
      const p2 = document.createElement("p");
      p2.classList.add("subText");
      p2.textContent = "バブルを大きくしよう";
      
      const button = document.createElement("button");
      button.setAttribute("type", "button");
      button.classList.add("button");
      button.addEventListener("click", this.start.bind(this));
      button.innerText = "ゲストとしてゲーム開始";
      message.appendChild(p);
      message.appendChild(p2);
      message.appendChild(button);
      message.style.display = "block";
    }

  start(arg) {
    //arg.stopPropagation();  //更新時に1個落ちちゃうのを防ぐ
    if (this.gameStatus === "ready") {
      this.gameStatus = "canput";
      this.createNewbubble();
      this.resetMessage();
    }
    gamebox.style.display="block"
    nextcontainer.style.display="block"
    score.style.display="block"
  }

  restart(arg){
    this.init()
    arg.stopPropagation();
    if (this.gameStatus === "ready") {
      this.gameStatus = "canput";
      this.createNewbubble();
      this.resetMessage();
    }
    gamebox.style.display="block"
    nextgamebox.style.display="block"
    score.style.display="block"
  }

  createNewbubble() {
    if (this.gameover) {
      return;
    }
    this.level1 = Math.floor(Math.random() * 5);
    const radius = this.level2 * 10 + 20;
    const currentbubble = Bodies.circle(this.defaultX, 30, radius, {
      isSleeping: true, //static(固定)にする
      label: "bubble_" + this.level2,
      friction: friction,
      mass: mass,
      density:density,
      collisionFilter: {
        group: 0,
        category: object_categories.bubble_pending, 
        mask: object_categories.wall | object_categories.bubble,
      },
      render: {
        sprite: {texture: bubble_bg[this.level2]},
      },
    });
    this.currentbubble = currentbubble;

    //next
    Composite.add(this.engine.world, [currentbubble]);
    const nextBubblegamebox = document.querySelector(".next");
    const nextBubbleElement = document.createElement("div");
    nextBubbleElement.classList.add("next-bubble");
    nextBubbleElement.style.width = "80px";
    nextBubbleElement.style.height = "80px";
    nextBubbleElement.style.backgroundImage = `url(${nextbubble_bg[this.level1]})`;
    nextBubbleElement.style.backgroundRepeat="no-repeat"
    this.level2=this.level1
    nextBubblegamebox.innerHTML = "";
    nextBubblegamebox.appendChild(nextBubbleElement);
  }

  putCurrentbubble() {
    Sleeping.set(this.currentbubble, false);
    this.currentbubble.collisionFilter.category = object_categories.bubble;
    this.currentbubble = undefined;
  }

  checkGameOver() {
    const bubbles = Composite.allBodies(this.engine.world).filter((body) =>
      body.label.startsWith("bubble_")
    );
    for (const bubble of bubbles) {
      if (bubble.position.y < height - deadline && bubble.velocity.y < 0) {
        Runner.stop(this.runner);
        this.gameover = true;
        this.showGameOverMessage();
        break;
      }
    }
  }

  showGameOverMessage() {
    this.valArray.push(this.val)
    
    
    //Javaに送信
    var data = this.val;
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "ScoreServlet", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("data=" + encodeURIComponent(data));

    
    const p = document.createElement("p");
    p.classList.add("mainText");
    p.textContent = "Game Over";
    
    const p2 = document.createElement("p");
    p2.classList.add("subText");
    p2.textContent = `Score: ${this.val}`;
   
    const button = document.createElement("button");
    button.setAttribute("type", "button");
    button.classList.add("button");
    button.addEventListener("click", this.restart.bind(this));
    button.innerText = "もう一度";
    
    const button2 = document.createElement("a");
    button2.href="ranking.jsp"
    button2.innerText = "本日のランキングを見る";
    
    const button3 = document.createElement("a");
    button3.href="index.jsp"
    button3.innerText = "signout";
    
    message.appendChild(p);
    message.appendChild(p2);
    message.appendChild(button);
    message.appendChild(button2);
    message.appendChild(button3);
    message.style.display = "block";
    score.style.display="none"
  }

  resetMessage() {
    message.replaceChildren(); //ノードを空にする
    message.style.display = "none";
  }

  handleClick() {
    if (this.gameover) {
      return;
    }
    if (this.gameStatus === "canput") {
      this.putCurrentbubble();
      this.gameStatus = "interval";
      setTimeout(() => {
        this.createNewbubble();
        this.gameStatus = "canput";
      }, 500);  //0.5秒後に実行
    }
  }

  handleCollision({ pairs }) {
    for (const pair of pairs) {
      const { bodyA, bodyB } = pair;
      // 既に衝突して消滅済みのバブルについての判定だった場合スキップ
      if (
        !Composite.get(this.engine.world, bodyA.id, "body") ||
        !Composite.get(this.engine.world, bodyB.id, "body")
      ) {
        continue;
      }
      if (bodyA.label === bodyB.label && bodyA.label.startsWith("bubble_")) {
        const currentbubbleLevel = Number(bodyA.label.substring(7));
        // スコア加算
        this.setScore(this.val + 2 ** currentbubbleLevel);  //2のcurrentbubbleLevel乗
        if (currentbubbleLevel === 11) {
          // 最大サイズの場合新たなバブルは生まれない
          Composite.remove(this.engine.world, [bodyA, bodyB]);
          continue;
        }
        const newLevel = currentbubbleLevel + 1;
        const newX = (bodyA.position.x + bodyB.position.x) / 2;
        const newY = (bodyA.position.y + bodyB.position.y) / 2;
        const newRadius = newLevel * 10 + 20;
        const newbubble = Bodies.circle(newX, newY, newRadius, {
          label: "bubble_" + newLevel,
          friction: friction,
          mass: mass,
          density:density,
          collisionFilter: {
            group: 0,
            category: object_categories.bubble,
            mask: object_categories.wall | object_categories.bubble,
          },
          render: {
            sprite: {texture: bubble_bg[newLevel]},
          },
        });
        Composite.remove(this.engine.world, [bodyA, bodyB]);
        Composite.add(this.engine.world, [newbubble]);
      }
    }
  }

  handleMouseMove(e) {
    if (this.gameStatus !== "canput" || !this.currentbubble) {
      return;
    }
    const { offsetX } = e;
    const currentbubbleRadius =
      Number(this.currentbubble.label.substring(7)) * 10 + 20;
    const newX = Math.max(
      Math.min(offsetX, width - 10 - currentbubbleRadius),
      10 + currentbubbleRadius
    );
    Body.setPosition(this.currentbubble, {
      x: newX,
      y: this.currentbubble.position.y,
    });
    this.defaultX = newX;
  }

  setScore(val) {
    this.val = val;
    score.replaceChildren(`Score: ${val}`);
  }
}

const game = new bubbleGame();
game.init();
