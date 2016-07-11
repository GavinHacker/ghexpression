/**
 * A simple instance of hashtable @ js, by gavinhacker.
 * */

function HashtableSimple(hashtable){this.obj = new Object();this.cnt = 0;this.source = hashtable; this.initialize();}

HashtableSimple.prototype = {
		
		contructor:HashtableSimple,
		
		initialize:function(){
			if(this.source == null){
				this.obj = {};
				this.cnt = 0;
				this.source = this.obj;
			}else{
				for(var key in this.source){
					this.obj[key] = this.source[key];
					++ this.cnt;
				}
			}
		},
		
		size:function(){
			return this.cnt;
		},
		
		items:function(key){
			if(this.contains(key)){
				return this.obj[key];
			}
		},
		
		get:function(key){
			return this.items(key);
		},
		
		put:function(key, value){
			if(this.obj.hasOwnProperty(key)){
				return false;
			}else{
				if(this.source == null || this.source == undefined){
					this.source = this.obj;
				}
				this.obj[key] = value;
				++ this.cnt;
				return true;
			}
		},
		
		clear:function(){
			this.obj = {};
			this.cnt = 0;
		},
		
		contains:function(key){
			return this.obj.hasOwnProperty(key);
		},
		
		remove:function(key){
			delete this.obj[key];
			--this.cnt;
		}
}