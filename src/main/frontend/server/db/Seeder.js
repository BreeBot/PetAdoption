import pg from "pg"

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@localhost:5432/adopt_a_pet"
})

class Seeder {
  static async seed() {
    try {
      await pool.query(
        "INSERT INTO pet_types (type, img_url, description) VALUES ('Dogs', 'http://animalsbreeds.com/wp-content/uploads/2015/12/Australian-kelpie-4.jpg', 'Best Friend!'), ('Monkeys', 'https://stoptrophyhuntingnow.files.wordpress.com/2014/02/monkeys-small-041.jpg', 'Fun Loving')"
      )
      await pool.query(
        "INSERT INTO adoptable_pets (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id ) VALUES ('Dog Marley', 'http://www.pawbuzz.com/wp-content/uploads/sites/551/2015/04/bulldog-surfing.jpg', '13', 'true', 'loves the water', 'available', '1');"
      )
      await pool.query(
        "INSERT INTO adoptable_pets (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id ) VALUES ('Red Dog', 'http://animalsbreeds.com/wp-content/uploads/2015/12/Australian-kelpie-4.jpg', '7', 'true', 'crossed vast lands in search of his master', 'available', '1');"
      )
      await pool.query(
        "INSERT INTO adoptable_pets (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id ) VALUES ('Biggie Smalls', 'https://www.lovethispic.com/uploaded_images/154657-Cute-Little-Monkey.jpg', '7', 'true', 'loves grapes', 'available', '2');"
      )
      await pool.query(
        "INSERT INTO adoptable_pets (name, img_url, age, vaccination_status, adoption_story, adoption_status, type_id ) VALUES ('Ginger', 'https://www.zastavki.com/pictures/640x480/2012/Animals_Monkeys_Ginger_Monkey_034205_29.jpg', '4', 'true', 'loves to be brushed', 'available', '2')"
      )

      pool.end()
    } catch (error) {
      console.error(error)
      pool.end()
    }
  }
}

export default Seeder
