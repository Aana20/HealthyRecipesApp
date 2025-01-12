package com.example.healthyrecipesapp

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val searchButton = findViewById<ImageView>(R.id.searchButton)

        // Colectează toate CheckBox-urile
        val checkBoxBreakfast = findViewById<CheckBox>(R.id.checkBox_breakfast)
        val checkBoxLunch = findViewById<CheckBox>(R.id.checkBox_lunch)
        val checkBoxDinner = findViewById<CheckBox>(R.id.checkBox_diner)
        val checkBoxDessert = findViewById<CheckBox>(R.id.checkBox_destert)
        val checkBoxSnacks = findViewById<CheckBox>(R.id.checkBox_snacks)

        val checkBoxSugarFree = findViewById<CheckBox>(R.id.checkBox_sugar_free)
        val checkBoxLowCarb = findViewById<CheckBox>(R.id.checkBox_low_carb)
        val checkBoxHighProtein = findViewById<CheckBox>(R.id.checkBox_high_protein)

        val checkBoxGlutenFree = findViewById<CheckBox>(R.id.checkBox_gluten_free)
        val checkBoxLactoseFree = findViewById<CheckBox>(R.id.checkBox_lactose_free)
        val checkBoxEggFree = findViewById<CheckBox>(R.id.checkBox_egg_free)

        val checkBoxChicken = findViewById<CheckBox>(R.id.checkBox_chicken)
        val checkBoxTon = findViewById<CheckBox>(R.id.checkBox_ton)
        val checkBoxBeef = findViewById<CheckBox>(R.id.checkBox_beef)
        val checkBoxEgg = findViewById<CheckBox>(R.id.checkBox_egg)
        val checkBoxTofu = findViewById<CheckBox>(R.id.checkBox_tofu)
        val checkBoxRice = findViewById<CheckBox>(R.id.checkBox_rice)
        val checkBoxOvaz = findViewById<CheckBox>(R.id.checkBox_ovaz)
        val checkBoxBrocoli = findViewById<CheckBox>(R.id.checkBox_brocoli)
        val checkBoxAvocado = findViewById<CheckBox>(R.id.checkBox_avocado)
        val checkBoxCartofDulce = findViewById<CheckBox>(R.id.checkBox_cartof_dulce)

        searchButton.setOnClickListener {
            val searchQuery = searchEditText.text.toString()
            val filters = mutableMapOf<String, Boolean>()
            // Adaugă toate filtrele în map
            filters["breakfast"] = checkBoxBreakfast.isChecked
            filters["lunch"] = checkBoxLunch.isChecked
            filters["dinner"] = checkBoxDinner.isChecked
            filters["dessert"] = checkBoxDessert.isChecked
            filters["snacks"] = checkBoxSnacks.isChecked

            filters["sugar_free"] = checkBoxSugarFree.isChecked
            filters["low_carb"] = checkBoxLowCarb.isChecked
            filters["high_protein"] = checkBoxHighProtein.isChecked

            filters["gluten_free"] = checkBoxGlutenFree.isChecked
            filters["lactose_free"] = checkBoxLactoseFree.isChecked
            filters["egg_free"] = checkBoxEggFree.isChecked

            filters["chicken"] = checkBoxChicken.isChecked
            filters["ton"] = checkBoxTon.isChecked
            filters["beef"] = checkBoxBeef.isChecked
            filters["egg"] = checkBoxEgg.isChecked
            filters["tofu"] = checkBoxTofu.isChecked
            filters["rice"] = checkBoxRice.isChecked
            filters["ovaz"] = checkBoxOvaz.isChecked
            filters["brocoli"] = checkBoxBrocoli.isChecked
            filters["avocado"] = checkBoxAvocado.isChecked
            filters["cartof_dulce"] = checkBoxCartofDulce.isChecked

            val intent = Intent(this, RecipeListActivity::class.java)
            intent.putExtra("searchQuery", searchQuery)
            intent.putExtra("filters", HashMap(filters))
            startActivity(intent)
        }

      // populateDatabase()
    }

    private fun populateDatabase() {
        val db = AppDatabase.getDatabase(this)
        val recipeDao = db.recipeDao()

        CoroutineScope(Dispatchers.IO).launch {
           recipeDao.deleteAllRecipes() // Șterge toate rețetele existente pentru a evita duplicarea

            recipeDao.insertRecipe(
                Recipe(
                    title = "Brioșe cu cacao sănătoase din 5 ingrediente - fără făină",
                    time = "25 min",
                    calories = 105,
                    servings = 4,
                    imageResId = "blueberry_muffins",
                    tags = "breakfast,dessert,snacks,gluten_free,lactose_free,low_carb",
                    ingredients = "2 oua,4 linguri de cacao,o lingura ulei de cocos topit,un vârf de cuțit de bicarbonat de sodiu",
                    instructions = "Amestecați toate ingredientele într-un castron folosind un tel.Turnați aluatul în forme pentru brioșe din silicon și coaceți-le timp de 20 de minute la 175 de grade Celsius (347 grade Fahrenheit). După ce s-au copt, lăsați-le să se răcească, apoi scoateți-le cu grijă din forme.Brioșele pot fi servite decorate cu iaurt grecesc și fructe",
                    protein = 4,
                    carbs = 10,
                    fats = 6
                )
            )

            recipeDao.insertRecipe(
                Recipe(
                    title = "Lipie sau tortilla din grâu integral coaptă umplută cu amestec de ton",
                    time = "15 min",
                    calories = 335,
                    servings = 1,
                    imageResId = "lipie_cu_ton",
                    tags = "breakfast,lunch,dinner,egg_free,high_protein,ton",
                    ingredients = "1 lipe din grau integral,70 g ton,30g mozzarella,2 muraturi,o bucata de ceapa,3 linguri porumb,mustar,ketchup",
                    instructions = "Mai întâi, pregătiți lipia. Întindeți deasupra muștarul, iar dedesubt ketchup-ul , apoi așezați pe jumătate de lipie tonul scurs de zeamă, ceapa, porumbul, murăturile, iar la final brânza dată pe răzătoare.Împăturiți lipia pe jumătate și apăsați-o pentru a nu se desprinde.Așezați-o într-o tigaie încălzită și coaceți-o pe ambele părți până când se rumenește.",
                    protein = 27,
                    carbs = 34,
                    fats = 10
                )
            )
            recipeDao.insertRecipe(
            Recipe(
                title = "Brioșe cu ou și ciuperci",
                time = "40",
                calories =56 ,
                servings = 12,
                imageResId = "briose_ou_ciuperci",
                tags = "breakfast,snacks,gluten_free,low_carb ,high_protein,egg",
                ingredients = "6 ouă de mărime medie,200 g brânză de vaci,200 g ciuperci brune tip champignon,1/2 ceapă roșie,2 căței de usturoi,1/2 linguriță sare,1/4 linguriță paprika,1/4 linguriță piper negru măcinat",
                instructions = "Într-un castron, amestecă ouăle cu brânza de vaci (scursă de lichid), ceapa roșie tăiată mărunt, cățeii de usturoi pisați, sarea și condimentele.Adaugă ciupercile spălate și tăiate mărunt.Toarnă aluatul în 12 forme pentru brioșe (de preferință din silicon).Coace brioșele sărate timp de 25-30 de minute la 180 de grade până când se rumenesc.",
                protein = 7,
                carbs = 2,
                fats =2
                )
            )
            recipeDao.insertRecipe(
                Recipe(
                    title = "Mousse proteic cu tofu",
                    time = "10",
                    calories = 275,
                    servings =1 ,
                    imageResId = "mouse_cu_tofu",
                    tags = "breakfast,snacks,gluten_free,low_carb ,high_protein,sugar_free,egg_free,tofu",
                    ingredients = "180 g (6.3oz) tofu alb bio,1 măsură de pudră proteică (o puteți înlocui cu 1 linguriță de esență de vanilie / 1 lingură de cacao + 3 linguri de miere,50 g (1.8oz) iaurt simplu,1 lingură unt de arahide (opțional),fructe uscate)",
                    instructions = "Amestecați toate ingredientele în blender și blenduiți-le bine.",
                    protein = 41,
                    carbs = 8,
                    fats =10
                )
            )
            recipeDao.insertRecipe(
                Recipe(
                    title = "Prăjiturele proteice pufoase cu banane",
                    time = "30",
                    calories =40 ,
                    servings =20 ,
                    imageResId = "biscuiti_cu_banana",
                    tags = "breakfast,dessert,lactose_free,ovaz,sugar_free,egg,",
                    ingredients = "2 banane coapte,2 ouă,30 g pudră proteică (la alegere),1 linguriță praf de copt ,1 linguriță praf de copt,120 ml lapte,30 g făină de cocos,stevia (după gust) ,bucățele de ciocolată neagră, nuci, semințe de chia, fructe uscate, nucă de cocos",
                    instructions = "Într-un castron, pisează bananele cu furculița până când obții un piure.Adaugă toate ingredientele lichide peste piure, amestecă, apoi adaugă toate ingredientele uscate. Aluatul trebuie să fie destul de tare.Folosind o lingură, ia câte o bucățică de aluat, modeleaz-o sub formă de prăjiturică și toarn-o într-o tavă tapetată cu hârtie pentru copt.Coace prăjiturile timp de 15-20 minute la 170 de grade până când se rumenesc.",
                    protein = 3,
                    carbs = 5,
                    fats =1
                )
            )
            recipeDao.insertRecipe(
                Recipe(
                    title = "Wrap low carb cu salată verde",
                    time = "5",
                    calories = 261,
                    servings = 1,
                    imageResId ="wrap_avocado",
                    tags = "breakfast,avocado,high_protein",
                    ingredients = "2 frunze mari de salată verde,50 g șuncă presată de calitate,30 g brânză feliată (de ex. șvaițer),1/4 avocado,1/2 roșie,piper",
                    instructions = "Amestecă pasta de avocado cu un vârf de cuțit de piper negru și întinde-o peste frunzele de salată, una câte una.Așează feliile de șuncă presată și de brânză peste frunzele de salată, iar deasupra așează o felie subțire de roșie.",
                    protein = 20,
                    carbs = 7,
                    fats =17
                )
            )
            recipeDao.insertRecipe(
                Recipe(
                    title = "Pizza-buzunar cu ou ușor de preparat (Calzone)",
                    time = "45",
                    calories =626 ,
                    servings = 1,
                    imageResId = "pizza",
                    tags = "lunch,dinner,high_protein,egg",
                    ingredients = "3 ouă,60 g făină spelta integrală (sau hrișcă),1 cățel de usturoi pisat,1/2 linguriță sare,30 g piure de tomate,70 g șuncă de calitate,50 g ciuperci (de ex. champignon)",
                    instructions = "mestecă toate ingredientele pentru aluatul de pizza cu ou.Toarnă aluatul într-o tavă tapetată cu hârtie pentru copt (cu diametrul de aproximativ 26 cm).oace aluatul timp de 20 de minute la 190 de grade, scoțându-l din cuptor la jumătatea timpului de coacere și înțepându-l de câteva ori cu o furculiță.Adauga umplutura",
                    protein =50 ,
                    carbs =51 ,
                    fats =23
                )
            )
            recipeDao.insertRecipe(
                Recipe(
                    title = "Pulpe de pui la cuptor în marinată cu lămâie",
                    time = "80",
                    calories = 555,
                    servings = 4,
                    imageResId = "pui",
                    tags = "lunch,dinner,high_protein,chicken,gluten_free,low_carb,lactose_free",
                    ingredients = "1 kilogram și jumătate de pulpe de pui (cu pieliță și oase),3 căței de usturoi pisați,sucul și coaja de la 1 lămâie,1 linguriță paprika,1/2 linguriță piper negru măcinat",
                    instructions = "Într-un castron, amestecă toate ingredientele pentru marinată.Adaugă pulpele de pui și lasă-le la marinat, sau întinde marinata pe toate părțile folosind o pensulă.Coace-le timp de 30 de minute la 200 de grade. În continuare, întoarce bucățile pe cealaltă parte și coace-le timp de încă 10 minute. Dacă pulpele sunt mai mari, le poți coace ceva mai mult timp. Dacă dorești ca suprafața pulpelor să fie crocantă, stropește-le din când în când cu sos pe măsură ce se coc.",
                    protein =69 ,
                    carbs = 3,
                    fats =30
                )
            )
            recipeDao.insertRecipe(
                Recipe(
                    title = "Conopidă în aluat la cuptor",
                    time = "55",
                    calories = 380,
                    servings =2 ,
                    imageResId = "conopida",
                    tags = "lunch,dinner,high_protein,egg,gluten_free,low_carb,lactose_free",
                    ingredients = "1 conopidă,2 ouă,7 linguri făină ,",
                    instructions = "Desfă conopida în buchețele, spală-le și dă-le prin ou bătut pe toate părțile.Într-o pungă resigilata, amestecă făina preferată cu sare, apoi introdu buchețelele de conopidă.Scutură punga de câteva ori până când conopida este acoperită de făină pe toate părțile, apoi scoate cu grijă bucățile de conopidă și așează-le într-o tavă tapetată cu hârtie pentru copt.Coace conopida timp de 40 de minute la 200 °C.",
                    protein = 20,
                    carbs = 39,
                    fats =13
                )
            )
            recipeDao.insertRecipe(
                Recipe(
                    title = "Gulaş de vită italian",
                    time = "60",
                    calories = 350,
                    servings = 2,
                    imageResId = "vita",
                    tags = "beef,lunch,dinner,high_protein,gluten_free,low_carb",
                    ingredients = "400 g carne de vită,1 ceapă,condimente,usturoi,piure rosii,apa",
                    instructions = "Sotează ceapa tăiată fin în o lingură de ulei până se rumeneşte, apoi adaugă carnea tăiată cubuleţe.Amestecă, adaugă sarea şi condimentele, 700 ml apă şi fierbe la foc mediu timp de 40 minute, amestecând din când în când.Apoi adaugă 3-4 linguri cu piure de roşii, usturoiul pisat şi, dacă este necesar, mai toarnă puţină apă.Lasă carnea pe foc încă 10 minute.",
                    protein = 49,
                    carbs =3 ,
                    fats =15
                )
            )
            recipeDao.insertRecipe(
                Recipe(
                    title = "Burrito fără carne pe farfurie",
                    time = "40",
                    calories =403 ,
                    servings = 2,
                    imageResId = "orez",
                    tags = "lunch,dinner,rice",
                    ingredients = "150 g (5.3oz) orez (brun/sălbatic/basmati) ,150 g (5.3oz) fasole fiartă,80 g (2.8oz) porumb,200 g (7.1oz) roșii cherry,80 g (2.8oz) mozzarella răzuită,condimente",
                    instructions = "Spălați orezul, puneți-l la fiert într-o cratiță cu o cantitate dublă de apă, adăugați sarea, pudra de usturoi și piperul negru măcinat și fierbeți-l până când se absoarbe toată apa (aproximativ 20 de minute).Cât încă este fierbinte, turnați orezul într-un castron și amestecați-l cu mozzarella răzuită și brânza de capră.adaugati legumele,La final, adăugați piperul roșu măcinat, busuiocul, un vârf de cuțit de condiment de ardei iute, iar dacă este necesar, sare și piper după gust.",
                    protein = 25,
                    carbs =40,
                    fats =14
                )
            )
            recipeDao.insertRecipe(
                Recipe(
                    title = "Salată cu broccoli, afine și semințe de mac",
                    time = "15",
                    calories =164 ,
                    servings = 4,
                    imageResId = "brocoli",
                    tags = "brocoli,snacks",
                    ingredients = "500 g broccoli,200 g afine,250 g iaurt grecesc simplu,1 măr,2 linguri semințe de mac date prin râșniță,miere",
                    instructions = "Într-un castron mai mare, amestecă iaurtul cu sucul de lămâie, mierea și semințele de mac date prin râșniță.Spală broccoli și mărul, șterge-le și taie-le în bucăți mai mici.Spală afinele și adaugă-le peste dressingul de iaurt împreună cu bucățile de broccoli și măr.",
                    protein =11 ,
                    carbs = 21,
                    fats =3
                )
            )
            recipeDao.insertRecipe(
                Recipe(
                    title = "Înghețată din cartof dulce cu biluțe de ciocolată și ovăz",
                    time = "45",
                    calories = 195,
                    servings =2 ,
                    imageResId = "cartof_dulce",
                    tags = "snacks,cartof_dulce,dessert,ovaz",
                    ingredients = "250 g cartofi dulci (2 cartofi mai micuți),1 cană de lapte,30 g curmale,ovaz,nuci,piure de mere,ciocolata",
                    instructions = "Așează cartofii dulci pe o hârtie de copt și coace-i la 200 °C până se înmoaie. Cartofii de mărimea unui pumn trebuie să stea la cuptor 30 de minute, cei mai mari de atât timp de 45 de minute.Lasă cartofii copți să se răcească, apoi taie-i, scoate-le miezul, amestecă miezul cu o furculiță și pune piureul într-un recipient cu capac.Adaugă laptele în recipient și dă amestecul la congelator timp de cel puțin 5 ore.Într-un blender, amestecă ovăzul, nucile caju, mierea, scorțișoara și piureul de mere până când se omogenizează.Adaugă fulgii de ciocolată în amestecul cu ovăz, apoi modelează-l în formă de biluțe. Ține biluțele la frigider până în momentul în care servești înghețata.",
                    protein = 5,
                    carbs = 38,
                    fats =3
                )
            )
            /*
            * recipeDao.insertRecipe(
Recipe(
                title = "",
                time = "",
                calories = ,
                servings = ,
                imageResId = "",
                tags = "",
                ingredients = "",
                instructions = "",
                protein = ,
                carbs = ,
                fats =
            )
            ) */
        }
    }


}
