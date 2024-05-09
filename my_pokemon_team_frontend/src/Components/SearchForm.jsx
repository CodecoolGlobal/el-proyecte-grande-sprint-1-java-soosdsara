import { useState } from "react";

function SearchForm({ onSubmit }) {
  const [search, setSearch] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(search);
  };

  return (
    <form onSubmit={handleSubmit} className="search_form">
      <input
        name="pokemon"
        value={search}
        placeholder={"...type a pokemon"}
        onChange={(e) => setSearch(e.target.value)}
      />
      <button type="submit">Search</button>
    </form>
  );
}

export default SearchForm;
